package edu.metcs683.walkabout.uihelper;

import edu.metcs683.walkabout.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Class to display error messages.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class ErrorDisplay {

	public static void displayMessage(Activity activity, Context context,
			String errorMessage) {
		ErrorDisplay.displayMessage(activity, context, errorMessage, null);
	}

	public static void displayMessage(Activity activity, Context context,
			String errorMessage, Exception ex) {
		final String title = context.getString(R.string.delete_image_text);
		String message = context.getString(R.string.error_message) + ": "
				+ errorMessage + "\n" + ex.getMessage();
		
		if (ex != null) {
			message += message + "\n" + ex.getMessage();
		}
		
		final String ok = context.getString(R.string.OK_button_label);

		new AlertDialog.Builder(activity).setTitle(title).setMessage(message)
				.setCancelable(false)
				.setPositiveButton(ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						// No need to do anything.
					}
				}).show();
	}
}
