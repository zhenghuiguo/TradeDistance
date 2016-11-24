package api;

public interface HttpCallbackListener {
	public void OnFinish(String response);
	public void OnError(Exception e);
}
