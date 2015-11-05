package com.facebook.avnp;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Facebook415Utils {
	// publish_actions
	private static final String PERMISSION = "publish_actions";
	private AppInviteDialog appInviteDialog;
	private Activity activity;

	public interface IFacebookListener {

		/**
		 * 
		 */
		public void inviteSuccess(com.facebook.share.widget.AppInviteDialog.Result result);

		public void inviteOnError(FacebookException error);

		public void inviteOnCancel();
	}

	public void onCreate(Activity activity, Bundle savedInstanceState, final IFacebookListener callbaclLogin) {
		this.activity = activity;
		FacebookSdk.sdkInitialize(activity.getApplicationContext());
		callbackManager = CallbackManager.Factory.create();

		/**
		 * share
		 */

		appInviteDialog = new AppInviteDialog(activity);
		appInviteDialog.registerCallback(getCallbackManager(), new FacebookCallback<AppInviteDialog.Result>() {

			@Override
			public void onSuccess(com.facebook.share.widget.AppInviteDialog.Result result) {
				callbaclLogin.inviteSuccess(result);
			}

			@Override
			public void onError(FacebookException error) {
			}

			@Override
			public void onCancel() {
			}
		});

	}

	private CallbackManager callbackManager;

	public CallbackManager getCallbackManager() {
		if (callbackManager == null) {
			callbackManager = CallbackManager.Factory.create();
		}

		return callbackManager;
	}

	/**
	 * 
	 */
	private Facebook415Utils() {

	}

	public void onPause() {
		AppEventsLogger.deactivateApp(activity);
	}

	public void onResume() {
		AppEventsLogger.activateApp(activity);

	}

	private String PENDING_ACTION_BUNDLE_KEY() {
		return String.format("%s:PendingAction", activity.getPackageName());
	}

	public void onDestroy() {
		try {
			profileTracker.stopTracking();
		} catch (Exception ed) {
		}
	}

	private ProfileTracker profileTracker;
	private static Facebook415Utils facebook415Utils = new Facebook415Utils();

	public static Facebook415Utils getInstance() {
		return facebook415Utils;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Log.e("ABCDS", ":" + resultCode + " : " + resultCode + " : " +
		// data.getExtras());
		// Bundle bundle = data.getExtras();
		// Set<String> key = bundle.keySet();
		// for (String str : key) {
		// Log.e("ABCDS1", str + " : " + bundle.getString(str));
		// }
		getCallbackManager().onActivityResult(requestCode, resultCode, data);
	}

	public void onSaveInstanceState(Bundle outState) {
		outState.putString(PENDING_ACTION_BUNDLE_KEY(), pendingAction.name());
	}

	private PendingAction pendingAction = PendingAction.NONE;

	private enum PendingAction {
		NONE, POST_PHOTO, POST_STATUS_UPDATE
	}

	public void invite() {
		String appLinkUrl, previewImageUrl;

		appLinkUrl = "https://fb.me/1680769912165728";
		previewImageUrl = "http://app.friendoapp.com/storage/images/invite.png";

		if (AppInviteDialog.canShow()) {
			AppInviteContent content = new AppInviteContent.Builder().setApplinkUrl(appLinkUrl)
					.setPreviewImageUrl(previewImageUrl).build();
			appInviteDialog.show(activity, content);
		}
	}
}