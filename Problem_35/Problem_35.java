class MovingAverage {
    private int size;
    private int num;
    private List<Integer> list;
    private double currentSum = 0;private double movingavg=0.0;
    public MovingAverage(int size) {
        this.size = size;
        this.num=0;
        this.list=new ArrayList<>();
    }
    
    public double next(int val) {
        list.add(val);
        num++;
        if(num<=size){
            currentSum+=val;
            movingavg = currentSum/num;
        }
        else{
            currentSum= currentSum-list.get(num-size-1);
            currentSum=currentSum+val;
            movingavg = currentSum/size;
        }
        return movingavg;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
