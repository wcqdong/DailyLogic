package com.wcq.other;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 电梯系统
 * 写的比较粗糙，缺少严格验证和检查，忽略了电梯停留时间，只关注电梯的选择和整体的调度
 */
public class ElevatorSys {
    /** 楼层高度 */
    private static int MAX_FLOOR = 1000;
    /** 电梯速度 2000毫秒走一层 */
    private static int SPEED = 2000;
    /** 电梯个数 */
    private static int ELEVATOR_NUM = 6;

    private Thread thread;

    private Random random;



    /** 电梯 */
    private Elevator[] elevators = new Elevator[ELEVATOR_NUM];

    /** 等待队列 */
    private List<Task> waitTasks = new ArrayList<>();

    /** 请求队列，线程不安全 */
    private final ConcurrentLinkedQueue<Task> calls = new ConcurrentLinkedQueue<>();
    /** 本次心跳要处理的请求 */
    private List<Task> pulseCalls = new ArrayList<>();

    public ElevatorSys(){
        thread = new Thread(() -> {
            // 休息20毫秒
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Task call;
            while ((call = calls.poll()) != null) {
                // 放入安全队列
                pulseCalls.add(call);
            }

            // 处理请求
            for(Task c : pulseCalls){
                click(c);
            }
            pulseCalls.clear();

            // 电梯移动
            long now = System.currentTimeMillis();
            for(Elevator e : elevators){
                e.pulse(now);
            }
            // 等待的任务
            for(Iterator<Task> iter = waitTasks.iterator(); iter.hasNext();){
                if(click(iter.next())){
                    iter.remove();
                }
            }

        });
    }

    /**
     * 点击选择楼层
     * @param curFloor 当前所在楼层
     * @param dir 选择上下楼
     */
    public void click(int curFloor, int dir){
        Task task = new Task(curFloor, dir);
        calls.add(task);
    }
    public boolean click(Task task){
        Elevator elevator = nearBy(task.fromFloor, task.dir);
        // 没找到合适的电梯，加入等待队列
        if(elevator == null || elevator.hasTask()){
            waitTasks.add(task);
            return false;
        }
        elevator.addTask(task);
        return true;
    }

    /**
     * 点击打开
     * @param floor
     */
    public void clickOpen(int floor){

    }

    /**
     * 最近的电梯
     * 计算到达curFloor时间最短的电梯
     * @param curFloor  当前所在楼层
     * @param dir 方向
     * @return
     */
    public Elevator nearBy(int curFloor, int dir){
        Elevator result = null;
        long time = Long.MIN_VALUE;
        for(Elevator e : elevators){
            long costTime = e.predictArriveTime(curFloor, dir);
            if(costTime == Long.MAX_VALUE){
                continue;
            }
            if(costTime < time){
                time = costTime;
                result = e;
            }
        }
        return result;
    }

    private boolean isSameDir(int dir, int dir1) {
        // 停止的也可以用，视为同方向
        if(dir == 0 || dir1 == 0){
            return true;
        }
        // 相同方向的
        if((dir > 0 && dir1 > 0) || (dir < 0 || dir1 < 0)){
            return true;
        }
        return false;
    }

    /**
     * 电梯
     */
    private class Elevator{
        /** 当前所在楼层 */
        private int curFloor;
        /** 移动中时，到达curFloor的时间 */
        private long time;
        /** 目标楼层 */
        private Task task;


        public void addTask(Task task) {
            this.task = task;
            startMove();
        }

        private void startMove() {
            time = System.currentTimeMillis();
        }

        public void pulse(long now) {
            if(task == null){
                return;
            }
            long diffTime = now - time;
            while(diffTime > SPEED){
                time += SPEED;
                // 楼层变化
                curFloor += task.dir;
                if(isArrive()){
                    time = now;
                    break;
                }
                diffTime -= SPEED;
            }
        }

        private boolean isArrive() {
            // 无目标楼层
            if(task.toFloor == -1){
                // 到达,模拟乘客选择了电梯楼层
                if(task.fromFloor == curFloor){
                    task.randomToFloor();
                    return true;
                }else{
                    return false;
                }

            }else{
                // 到达,清除任务
                if(task.toFloor == curFloor){
                    task = null;
                    time = 0;
                    return true;
                }else{
                    return false;
                }
            }

        }

        public long predictArriveTime(int floor, int dir){
            if(task == null){
                return Math.abs(floor - curFloor) * SPEED;
            }
            // 乘客还没选择去几层
            if(task.toFloor == -1){
                if(isBetween(floor, dir, curFloor, task.fromFloor)){
                    return Math.abs(curFloor - floor) * SPEED;
                }else{
                    return Long.MAX_VALUE;
                }
            }else{
                if(isBetween(floor, dir, curFloor, task.toFloor)){
                    return Math.abs(curFloor - floor) * SPEED;
                }else{
                    return (Math.abs(curFloor - task.toFloor) + Math.abs(task.toFloor - floor)) * SPEED;
                }
            }
        }

        public boolean hasTask() {
            return  task != null;
        }
    }

    boolean isBetween(int cur, int dir, int from, int to){
        int dir1 = to - from;
        if(!isSameDir(dir, dir1)){
            return false;
        }
        if(dir > 0){
            return cur >= from && cur <= to;
        }else{
            return cur >= to && cur <= from;
        }
    }

    private class Task{
        private int fromFloor;
        private int dir;
        private int toFloor = -1;

        public Task(int curFloor, int dir) {
            this.fromFloor = curFloor;
            this.dir = dir;
        }

        public void randomToFloor() {
            if(dir > 0){
                toFloor += random.nextInt((MAX_FLOOR-1) - fromFloor) + 1;
            }else{
                toFloor -= random.nextInt(fromFloor - 1) + 1;
            }
        }

//        public Task(int curFloor, int targetFloor) {
//            this.curFloor = curFloor;
//            this.targetFloor = targetFloor;
//        }
    }

}
