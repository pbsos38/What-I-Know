# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-keep class com.squareup.okhttp.* { ;}
#-dontwarn com.squareup.okhttp.**
#-keep class com.squareup.retrofit.* { ;}
#-keep class com.squareup.okhttp3.* { ;}
#-keep class com.chaos.view.* { ;}
#-keep class com.github.bumptech.glide.* { ;}
#
#
## one signal Starts
#-dontwarn com.onesignal.**
#
## These 2 methods are called with reflection.
#-keep class com.google.android.gms.common.api.GoogleApiClient {
#    void connect();
#    void disconnect();
#}
#
#
#-keep class com.onesignal.ActivityLifecycleListenerCompat** {*;}
#
#
## Observer backcall methods are called with reflection
#-keep class com.onesignal.OSSubscriptionState {
#    void changed(com.onesignal.OSPermissionState);
#}
#
#-keep class com.onesignal.OSPermissionChangedInternalObserver {
#    void changed(com.onesignal.OSPermissionState);
#}
#
#-keep class com.onesignal.OSSubscriptionChangedInternalObserver {
#    void changed(com.onesignal.OSSubscriptionState);
#}
#
#-keep class ** implements com.onesignal.OSPermissionObserver {
#    void onOSPermissionChanged(com.onesignal.OSPermissionStateChanges);
#}
#
#-keep class ** implements com.onesignal.OSSubscriptionObserver {
#    void onOSSubscriptionChanged(com.onesignal.OSSubscriptionStateChanges);
#}
#
#-keep class com.onesignal.shortcutbadger.impl.AdwHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.ApexHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.AsusHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.DefaultBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.EverythingMeHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.HuaweiHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.LGHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.NewHtcHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.NovaHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.OPPOHomeBader { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.SamsungHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.SonyHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.VivoHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.XiaomiHomeBadger { <init>(...); }
#-keep class com.onesignal.shortcutbadger.impl.ZukHomeBadger { <init>(...); }
#-dontwarn com.amazon.**
#-dontwarn com.huawei.**
## Proguard ends up removing this class even if it is used in AndroidManifest.xml so force keeping it.
#-keep public class com.onesignal.ADMMessageHandler {*;}
#-keep class com.onesignal.JobIntentService$* {*;}
## one signal ends


# -keep class com.shockwave.** // for pdf view



