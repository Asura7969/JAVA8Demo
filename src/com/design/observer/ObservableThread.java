package com.design.observer;

public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> lifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    //指定 TaskLifeCycle 的同时指定 Task
    public ObservableThread(TaskLifeCycle<T> lifeCycle,Task<T> task){
        super();
        //Task 不允许为 null
        if(null == task){
            throw new IllegalArgumentException("The task is required.");
        }
        this.lifeCycle = lifeCycle;
        this.task = task;
    }

    //指定 Task 的实现,默认情况下使用 EmptyLifeCycle
    public ObservableThread(Task<T> task){
        this(new TaskLifeCycle.EmptyLifeCycle<>(),task);
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    @Override
    public final void run() {
        //在执行线程逻辑单元的时候,分别触发相应的事件
        this.update(Cycle.STARTED,null,null);

        try {
            this.update(Cycle.RUNNING,null,null);
            T result = this.task.call();
            this.update(Cycle.DONE,result,null);
        }catch (Exception e){
            this.update(Cycle.ERROR,null,e);
        }
    }

    private void update(Cycle cycle, T result, Exception e){
        this.cycle = cycle;
        if(null == lifeCycle){
            return;
        }
        try {
            switch (cycle){
                case STARTED:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifeCycle.onFinish(currentThread(),result);
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(),e);
                    break;
            }
        }catch (Exception ex){
            if(cycle == Cycle.ERROR){
                throw ex;
            }
        }

    }
}
