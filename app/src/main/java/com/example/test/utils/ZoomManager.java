//package com.example.test.utils;
//
//import android.content.Context;
//
//import us.zoom.sdk.JoinMeetingOptions;
//import us.zoom.sdk.JoinMeetingParams;
//import us.zoom.sdk.MeetingService;
//import us.zoom.sdk.ZoomSDK;
//import us.zoom.sdk.ZoomSDKInitParams;
//import us.zoom.sdk.ZoomSDKInitializeListener;
//
//public class ZoomManager {
//
//
//    public void initializeSdk(Context context) {
//        ZoomSDK sdk = ZoomSDK.getInstance();
//        // TODO: Do not use hard-coded values for your key/secret in your app in production!
//        ZoomSDKInitParams params = new ZoomSDKInitParams();
//        params.appKey = "m8Rjq0u9BZlNvMgN7vIhG3XNfXW8N36ndKeD"; // TODO: Retrieve your SDK key and enter it here
//        params.appSecret = "9kqx48Xm0pkby6snGPG0OhCwxhoub00LolvS"; // TODO: Retrieve your SDK secret and enter it here
//        params.domain = "zoom.us";
//        params.enableLog = true;
//        // TODO: Add functionality to this listener (e.g. logs for debugging)
//        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
//            /**
//             * @param errorCode {@link us.zoom.sdk.ZoomError#ZOOM_ERROR_SUCCESS} if the SDK has been initialized successfully.
//             */
//            @Override
//            public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) {}
//
//            @Override
//            public void onZoomAuthIdentityExpired() {}
//        };
//        sdk.initialize(context, listener, params);
//    }
//    public void joinMeeting(Context context, String meetingNumber, String password, String username) {
//        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
//        JoinMeetingOptions options = new JoinMeetingOptions();
//        JoinMeetingParams params = new JoinMeetingParams();
//        params.displayName = username; // TODO: Enter your name
//        params.meetingNo = meetingNumber;
//        params.password = password;
//        meetingService.joinMeetingWithParams(context, params, options);
//    }
//
//}
