package com.Texala.Metro.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.Texala.Metro.Repo.HardCodedDataRepo;
import com.Texala.Metro.pojo.CardTrx;
import com.Texala.Metro.pojo.SmartCard;
import com.Texala.Metro.pojo.Station;
import com.Texala.Metro.Exceptions.InsufficientCardBalance;
import com.Texala.Metro.Exceptions.MinimumCardBalanceException;

@Service
public class MetroService {
	private ConcurrentMap<Station, AtomicInteger> stationFootFall = new ConcurrentHashMap<>();

    private HardCodedDataRepo trxRepository = new HardCodedDataRepo();
    private FareCalculator fareCalculator = new FareCalculator();

    public void swipeIn(SmartCard card, Station source, LocalDateTime dateTime) throws MinimumCardBalanceException {
        if (card.getBalance() < 5.5) {
            throw new MinimumCardBalanceException("Minimum balance of Rs 5.5 is required at Swipe In");
        }
        stationFootFall.putIfAbsent(source, new AtomicInteger());
        stationFootFall.get(source).incrementAndGet();
        CardTrx trx = new CardTrx();
        trx.setSource(source);
        trx.setCard(card);
        trx.setStartTime(dateTime);
        trxRepository.addTransientTrx(card, trx);
    }

    public void swipeOut(SmartCard card, Station destination, LocalDateTime dateTime)  {
        stationFootFall.putIfAbsent(destination, new AtomicInteger());
        stationFootFall.get(destination).incrementAndGet();
        CardTrx trx = trxRepository.getTransientTrx(card);
        trx.setDestination(destination);
        trx.setEndTime(dateTime);
        trx.setDistance(destination.distance(trx.getSource()));
        trx.setFare(fareCalculator.getFare(trx.getSource(), trx.getDestination(), dateTime));
        if (trx.getFare() > card.getBalance()) {
            throw new InsufficientCardBalance("Insufficient balance at Swipe Out, Please recharge card and try again");
        }
        trx.setFareStrategyUsed(FareStrategyFactory.getFareStrategy(dateTime));
        trx.setBalance(card.getBalance() - trx.getFare());
        card.setBalance(card.getBalance() - trx.getFare());
        trxRepository.addCompletedTrx(card, trx);
    }


    public int calculateFootFall(Station station) {
        return stationFootFall.getOrDefault(station, new AtomicInteger(0)).get();
    }

    public List<CardTrx> cardReport(SmartCard card) {
        List<CardTrx> trxs = trxRepository.getCompletedTrxs(card);
        trxs.forEach(trx -> {
            System.out.println("trx = " + trx);
        });
        return trxs;
    }
}
