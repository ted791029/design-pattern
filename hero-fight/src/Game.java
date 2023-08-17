import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Game {
    List<Hero> heroes;

    public Game(Hero... heroes) {
        setHeroes(new ArrayList<>(asList(heroes)));
    }

    public void start() {
        nextRound();
    }

    private void nextRound() {
        Hero attacker = heroes.get(0);
        Hero attacked = heroes.get(1);
        attacker.attack(attacked);
        if (attacked.isDead()) {
            System.out.printf("%s玩家死亡\n", attacked.getName());
            heroes.remove(attacked);
        }
        if (heroes.size() < 2) {
            System.out.printf("%s玩家獲勝\n", attacker.getName());
        } else {
            toEnd(attacker);
            nextRound();
        }
    }

    private void toEnd(Hero hero) {
        heroes.remove(hero);
        heroes.add(hero);
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
