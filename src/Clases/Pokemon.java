package Clases;

public class Pokemon { //Creo clase para poder iterar luego el array y obtener los datos con getter de la clase.
    String name;
    String type;
    String move1;
    String move2;
    String move3;
    int hp;
    int attack;
    int defense;
    int speed;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    String urlImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getMove3() {
        return move3;
    }

    public void setMove3(String move3) {
        this.move3 = move3;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Pokemon(){

    }

    public Pokemon(String name, String type, String move1, String move2, String move3, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.type = type;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append("Name: " + name + ", Type: " + type +
                " ,Moves: " + move1 + ", " + move2 + ", " + move3 + ", HP: " + hp +
                ", " + "Attack: " + attack + ", Defense: " + defense + ", Speed: " + speed
        + "Image: " + urlImage);
        return String.valueOf(st);
    }
}
