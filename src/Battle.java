import java.util.*;

public record Battle(Character character1, Character character2, List<Gear<?>> items) {
    public Battle(Character character1, Character character2, List<Gear<?>> items) {
        this.character1 = character1;
        this.character2 = character2;
        this.items = new ArrayList<>(items);
    }

    public void startBattle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Battle starts between " + character1.getName() + " and " + character2.getName() + "!\n");
        for (int i = 0; i < 10; i++) {
            System.out.println("--------- Round " + (i + 1) + " ---------");
            PriorityQueue<Gear<?>> queue1 = new PriorityQueue<>((item1, item2) -> {
                int compare = Integer.compare(character1.gearSlot(item2.getGearType().getClass().toString()),
                        character1.gearSlot(item1.getGearType().getClass().toString()));
                if (compare == 0) {
                    compare = Integer.compare(item2.getAttack(), item1.getAttack());
                    if (compare == 0) {
                        compare = Integer.compare(item2.getDefense(), item1.getDefense());
                    }
                }
                return compare;
            });
            queue1.addAll(items);
            Gear<?> pickedItem1 = queue1.poll();
            character1.pickupGear(pickedItem1);
            items.remove(pickedItem1);
            System.out.println(character1.getName() + " is picking up a piece of " + pickedItem1);

            PriorityQueue<Gear<?>> queue2 = new PriorityQueue<>((item1, item2) -> {
                int compare = Integer.compare(character1.gearSlot(item2.getGearType().getClass().toString()),
                        character1.gearSlot(item1.getGearType().getClass().toString()));
                if (compare == 0) {
                    compare = Integer.compare(item2.getAttack(), item1.getAttack());
                    if (compare == 0) {
                        compare = Integer.compare(item2.getDefense(), item1.getDefense());
                    }
                }
                return compare;
            });
            queue2.addAll(items);
            Gear<?> pickedItem2 = queue2.poll();
            character2.pickupGear(pickedItem2);
            items.remove(pickedItem2);
            System.out.println(character2.getName() + " is picking up a piece of " + pickedItem2);

            String sb =
                    "-----------|--------|---------|-------------------------------------------|---------------------------------------------------------------------|--------------------------------------------------------------\n" +
                    "Name       | Attack | Defense | HeadGear                                  | FootGears                                                           | HandGears\n" +
                    "-----------|--------|---------|-------------------------------------------|---------------------------------------------------------------------|--------------------------------------------------------------\n" +
                    character1.printToTable() +
                    "-----------|--------|---------|-------------------------------------------|---------------------------------------------------------------------|--------------------------------------------------------------\n" +
                    character2.printToTable() +
                    "-----------|--------|---------|-------------------------------------------|---------------------------------------------------------------------|--------------------------------------------------------------";
            System.out.println(sb);

            if (i != 9) System.out.println("Press ENTER key to continue to the next round...");
            else System.out.println("Press ENTER key to see the winner:");
            scanner.nextLine();
        }

        Character winner = winnerOfGame(character1, character2);
        if (winner != null) {
            System.out.println(winner.getName() + " wins the battle!");
        } else {
            System.out.println("The game is a tie!");
        }
    }

    public Character winnerOfGame(Character character1, Character character2) {
        int damage1 = character1.calculateDamage(character2);
        int damage2 = character2.calculateDamage(character1);
        if (damage1 > damage2) return character1;
        else if (damage1 < damage2) return character2;
        else return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Battle Information:\nCharacter 1 Name: ").append(character1.getName());
        sb.append("\nCharacter 2 Name: ").append(character2.getName()).append("\nList of Items:\n");
        for (Gear<?> item : items) {
            sb.append("  -  ").append(item).append("\n");
        }
        return sb.toString();
    }
}
