//The car objects arrive at the stoplight
// on 1 of two roads,
// with an intent to Turn left right or straight,
// arrive and depart at a specific time
class Car {
    private Road road;
    private Path turnIntent;

    int getArrivalTime() {
        return arrivalTime;
    }

    int getDepartureTime() {
        return departureTime;
    }

    //time variables arrival at intersection, departure from intersection and the intersection clear time
    private int arrivalTime, departureTime, clearTime;
    int getClearTime(){
        return clearTime;
    }
    boolean isClear=false;
    //constor for the car takes in which road it is and it's arrival time
    Car(Road road, int arrivalTime) {
        this.road = road;
        this.arrivalTime = arrivalTime;

        //here we generate which direction the car will turn
        int turnGenerator = (int) (Math.random() * 10);
        if (road == Road.EAST_WEST) {
            if (turnGenerator <= 6) {
                turnIntent = Path.EAST_SOUTH;
                clearTime = (int) (5 * Math.log(1 - Math.random()) / -1.0);
            } else {
                turnIntent = Path.EAST_NORTH;
                clearTime = (int) (8 * Math.log(1 - Math.random()) / -1.0);
            }
        } else if (road == Road.NORTH_SOUTH) {
            if (turnGenerator <= 7) {
                turnIntent = Path.NORTH_NORTH;
                clearTime = (int) (4 * Math.log(1 - Math.random()) / -1.0);
            } else {
                turnIntent = Path.NORTH_WEST;
                clearTime = (int) (10 * Math.log(1 - Math.random()) / -1.0);
            }
        }

    }

    void setDepartureTime(int time){
        this.departureTime=time;
    }
    Road getRoad() {
        return road;
    }
    Path getPath() {
        return turnIntent;
    }
}