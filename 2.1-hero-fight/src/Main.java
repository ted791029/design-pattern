public class Main {
    public static void main(String[] args) {
        Hero hero1 = new Hero("Ted", new Waterball());
        Hero hero2 = new Hero("Robbert", new Earth());
        Game game = new Game(hero1, hero2);
        game.start();
    }
}