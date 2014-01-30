package de.kimminich.kata.tcg.strategy;

import de.kimminich.kata.tcg.Card;
import org.junit.Before;
import org.junit.Test;

import static de.kimminich.kata.tcg.syntactic.CardSugar.card;
import static de.kimminich.kata.tcg.syntactic.CardSugar.noCard;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HighestCardFirstStrategyTest {

    Strategy strategy;

    @Before
    public void setUp() {
        strategy = new HighestCardFirstStrategy();
    }

    @Test
    public void shouldPlayCardsInOrderFromHighToLow() {
        assertThat(strategy.nextCard(10, Card.list(0, 2, 3, 8, 9)), is(card(9)));
        assertThat(strategy.nextCard(1, Card.list(0, 2, 3, 8)), is(card(0)));
    }

    @Test
    public void shouldReturnNoCardIfInsufficientManaForAnyHandCard() {
        assertThat(strategy.nextCard(1, Card.list(2, 3, 8)), is(noCard()));
    }

}
