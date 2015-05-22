package com.model.util.timer;

import java.rmi.ServerException;
import java.util.concurrent.RejectedExecutionException;



public interface KGameTimerTask {

	/**
	 * 时效任务的名称。没实际作用，只是作为跟踪辨别之用。
	 * 
	 * @return 时效任务的名称
	 */
	String getName();

	/**
	 * 到达预设的时间，收到定时器发来的定时信号，在本方法实现业务逻辑
	 * 
	 * @param timeSignal
	 *            报时信号
	 * @return 目前定义Object，使用者可根据自身实际需要定义
	 * @throws KGameServerException
	 */
	Object onTimeSignal(KGameTimeSignal timeSignal) throws ServerException;

	/**
	 * 当一轮逻辑处理完成后的通知，其实就是线程池执行完{@linkplain #onTimeSignal(KGameTimeSignal)
	 * onTimeSignal}方法时调用。<br>
	 * 本方法未必有实际的业务逻辑作用，或可以用于对任务执行的时间和结果的检测。。。<br>
	 * <br>
	 * PS：可以调用{@link KGameTimeSignal#get()}方法获取到返回值，也就是
	 * {@linkplain #onTimeSignal(KGameTimeSignal) onTimeSignal}返回的Object
	 * 
	 * @param timeSignal
	 */
	void done(KGameTimeSignal timeSignal);
	
	/**
	 * 执行线程池拒绝执行，此异常一般不会出现，除非执行线程池出现严重问题
	 * @param e 拒绝执行异常
	 */
	void rejected(RejectedExecutionException e);
	
}
