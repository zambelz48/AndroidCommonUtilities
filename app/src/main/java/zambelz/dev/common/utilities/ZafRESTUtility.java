package zambelz.dev.common.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ZafRESTUtility {

	private ZafJSONParser jParser = null;
	private JSONArray jArray = null;
	private List<NameValuePair> jParams = null;
	private JSONObject jRequest = null;
	private JSONObject jContent = null;

	public ZafRESTUtility() {
		jParser = new ZafJSONParser();
		jParams = new ArrayList<NameValuePair>();
	}

	public void setJParams(String key, String value) {
		jParams.add(new BasicNameValuePair(key, value));
	}

	public void initGET(String JSONUrl) {
		jRequest = jParser.makeHttpRequest(JSONUrl, "GET", jParams);
	}

	public void initPOST(String JSONUrl) {
		jRequest = jParser.makeHttpRequest(JSONUrl, "POST", jParams);
	}

	public void getJArray(String content) throws JSONException {
		jArray = jRequest.getJSONArray(content);
	}

	public int getContentLength() {
		return jArray.length();
	}

	public void getJObject(int position) throws JSONException {
		jContent = jArray.getJSONObject(position);
	}

	public String getJContent(String value) throws JSONException {
		return jContent.getString(value);
	}

	public int getJResult(String resultKey) throws JSONException {
		return jRequest.getInt(resultKey);
	}
}