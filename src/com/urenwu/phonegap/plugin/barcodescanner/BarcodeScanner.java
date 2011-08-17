/**
 * 
 */
package com.urenwu.phonegap.plugin.barcodescanner;

import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONException;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;
import com.phonegap.api.PluginResult.Status;

/**
 * @author hbb
 *
 */
public class BarcodeScanner extends Plugin
{
	
	public static final int REQUEST_CODE = 0;
	
	public static final String SCAN = "scan";

	private String callback;
	

	/** 
	 * PhoneGap plugin which can be involved in following manner from javascript
	 * @see com.phonegap.api.Plugin#execute(java.lang.String, org.json.JSONArray, java.lang.String)
	 */
	@Override
	public PluginResult execute(String action, JSONArray data, String callbackId)
	{
		PluginResult result = null;
		
		this.callback = callbackId;
		
		if (SCAN.equals(action))
		{
			scan();
			
			result = new PluginResult(PluginResult.Status.NO_RESULT);
			result.setKeepCallback(true);
		}
		else
		{
			result = new PluginResult(Status.INVALID_ACTION);
			Log.d("BarcodeScannerPlugin", "Invalid action : " + action + " passed");
		}
		return result;
	}
	
	/**
	 * Initiates a barcode scan. If the ZXing scanner isn't installed, the user
	 * will be prompted to install it.
	 * @param types	The barcode types to accept
	 * @param installTitle The title for the dialog box that prompts the user to install the scanner
	 * @param installMessage The message prompting the user to install the barcode scanner
	 * @param yesString The string "Yes" or localised equivalent
	 * @param noString The string "No" or localised version
	 */
	public void scan()
	{
	    Intent intentScan = new Intent("com.urenwu.phonegap.plugin.barcodescanner.SCAN");
	    intentScan.addCategory(Intent.CATEGORY_DEFAULT);

//	    // A null format means we scan for any type
//	    if (barcodeFormats != null) {
//			// Tell the scanner what types we're after
//			intentScan.putExtra("SCAN_FORMATS", barcodeFormats);
//	    }

	    try {
			this.ctx.startActivityForResult((Plugin) this, intentScan, REQUEST_CODE);
	    } catch (ActivityNotFoundException ex) {
	    	Log.d("BarcodeScannerPlugin", "Start Activity Exception: " + ex.getMessage());
	    	this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
	    }
	}
	
	/**
     * Called when the barcode scanner exits
     *
     * @param requestCode		The request code originally supplied to startActivityForResult(),
     * 							allowing you to identify who this result came from.
     * @param resultCode		The integer result code returned by the child activity through its setResult().
     * @param intent			An Intent, which can return result data to the caller (various data can be attached to Intent "extras").
     */
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if (requestCode == REQUEST_CODE) {
			if (resultCode == Activity.RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				this.success(new PluginResult(PluginResult.Status.OK, contents), this.callback);
			} else {
				this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
			}
		}
	}
}
