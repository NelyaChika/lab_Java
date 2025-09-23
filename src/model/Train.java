package model;

public class Train {
    private String destination;
    private int trainNumber;
    private String departureTime;
    private int generalSeats;
    private int coupeSeats;
    private int platzkartSeats;
    private int luxSeats;

    // Конструктор
    public Train(String destination, int trainNumber, String departureTime,
                 int generalSeats, int coupeSeats, int platzkartSeats, int luxSeats) {
        this.destination = destination;
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.generalSeats = generalSeats;
        this.coupeSeats = coupeSeats;
        this.platzkartSeats = platzkartSeats;
        this.luxSeats = luxSeats;
    }

    // Getters і setters (setValue/getValue)
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public int getTrainNumber() { return trainNumber; }
    public void setTrainNumber(int trainNumber) { this.trainNumber = trainNumber; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public int getGeneralSeats() { return generalSeats; }
    public void setGeneralSeats(int generalSeats) { this.generalSeats = generalSeats; }

    public int getCoupeSeats() { return coupeSeats; }
    public void setCoupeSeats(int coupeSeats) { this.coupeSeats = coupeSeats; }

    public int getPlatzkartSeats() { return platzkartSeats; }
    public void setPlatzkartSeats(int platzkartSeats) { this.platzkartSeats = platzkartSeats; }

    public int getLuxSeats() { return luxSeats; }
    public void setLuxSeats(int luxSeats) { this.luxSeats = luxSeats; }

    @Override
    public String toString() {
        return "Train{" +
                "destination='" + destination + '\'' +
                ", number=" + trainNumber +
                ", departureTime='" + departureTime + '\'' +
                ", seats=" + "[general=" + generalSeats +
                ", coupe=" + coupeSeats +
                ", platzkart=" + platzkartSeats +
                ", lux=" + luxSeats + "]" +
                '}';
    }
}

