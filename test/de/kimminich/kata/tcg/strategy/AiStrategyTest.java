package de.kimminich.kata.tcg.strategy;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static de.kimminich.kata.tcg.syntactic.CardSugar.card;
import static de.kimminich.kata.tcg.syntactic.CardSugar.noCard;
import static de.kimminich.kata.tcg.syntactic.StrategySugar.fromCards;
import static de.kimminich.kata.tcg.syntactic.StrategySugar.withMana;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AiStrategyTest {

    Strategy strategy;

    @Before
    public void setUp() {
        strategy = new AiStrategy();
    }

    @Test
    @Ignore // TODO Optimized damage combo building not implemented yet
    public void shouldTryToMaximizeDamageOutputInCurrentTurn() {
        assertThat(strategy.nextCard(withMana(8), fromCards(7, 6, 4, 2, 1)), either(is(card(2))).or(is(card(6))));
    }

    @Test
    public void shouldTryToReduceHandSizeToTwo() {
        assertThat(strategy.nextCard(withMana(4), fromCards(1, 1, 1, 1, 3)), is(card(1)));
        assertThat(strategy.nextCard(withMana(3), fromCards(1, 1, 1, 3)), is(card(1)));
        assertThat(strategy.nextCard(withMana(2), fromCards(1, 1, 3)), is(card(1)));
    }

    @Test
    public void shouldPickHighestAffordableCardFromHandOfSizeTwo() {
        assertThat(strategy.nextCard(withMana(6), fromCards(4, 5)), is(card(5)));
        assertThat(strategy.nextCard(withMana(1), fromCards(1, 2)), is(card(1)));
    }

    @Test
    public void shouldPlayLastCardIfAffordable() {
        assertThat(strategy.nextCard(withMana(6), fromCards(5)), is(card(5)));
    }

    @Test
    public void shouldReturnNoCardIfInsufficientManaForAnyHandCard() {
        assertThat(strategy.nextCard(withMana(1), fromCards(2, 3, 8)), is(noCard()));
    }

}