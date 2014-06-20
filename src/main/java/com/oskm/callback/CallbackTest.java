package com.oskm.callback;

public class CallbackTest {

	public void main() {

		CallBackable callback = new CallBackable() {
			
			@Override
			public void onClick() {
				
				
			}
		};
		
		doWork(callback);
	}
	
	public void doWork(CallBackable callback) {
		callback.onClick();
	}
}
