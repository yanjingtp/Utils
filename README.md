## 这是一个简单的工具类,主要是自己项目中用到的,后期会不断完善.
引用地址:implementation 'cn.yanjingtp.utils:utils:latest.release'

## LogUtils
fun <T> T?.logD()

fun <T> T?.logE()

## ToastUtil

**toast**
fun String.showLong(context: Context)

**toast**
fun String.show(context: Context)


## BarUtils

**获取状态栏高度**
fun getBarHeight(context: Context): Int



## ConvertUtils

**根据手机的分辨率从 dp 的单位 转成为 px(像素)**
fun dp2px(context: Context, dpValue: Float): Int


**根据手机的分辨率从 px(像素) 的单位 转成为 dp**
fun px2dp(context: Context, pxValue: Float): Int





## FileUtils

**复制单个文件**
fun copyFile(oldPathName: String?, newPathName: String?): Boolean

**从Uri 复制单个文件**
fun copyFileFromUri(context: Context,oldUri: Uri, newPathName: String): Boolean






## KeyboardUtils

**隐藏键盘**
fun hideKeyBoard(activity: Activity)





## SpanUtils

**富文本**
fun getSpannableString(target:String,origin:String,color:String): SpannableString





## base

**baseActivity**
BaseActivity

**baseViewModel**
BaseVM

**baseFragment**
BaseFragment





## widget

**可在scrollView中使用的EditText**
EditTextWithScrollView

**滚动的TextView**
MarqueTextView





## ActivityUtils

**回到桌面**
fun startHomeActivity(context: Context)

**判断Intent 是否有效**
fun isValidIntent(context: Context, intent: Intent?): Boolean





## AppUtils

**获取 App 图标**
fun getAppIcon(context: Context, packageName: String?): Drawable?


**获取 App 名称**
fun getAppName(context: Context, packageName: String?): String


**获取 App 版本号**
fun getAppVersionName(context: Context, packageName: String?): String


**获取 App 版本码**
fun getAppVersionCode(context: Context, packageName: String?): Int


**判断应用是否首次安装**
fun isFirstTimeInstalled(context: Context,packageName: String?): Boolean

**静默安装**
fun silentInstall(context: Context, apkPath: String): Boolean

**获取apk的包名**
fun getPkgName(context: Context, apkPath: String?): String?

**判断两个版本号哪个大**
fun checkVersion(versionOld: String?, versionNew: String?): Boolean





## ClipboardUtils

**复制文本到剪贴板**
fun copyText(context: Context,text: CharSequence?)

**获取剪贴板最近的文本**
fun getText(context: Context): CharSequence





## ColorUtils

**获取颜色**
fun getColor(context: Context,@ColorRes id: Int): Int

**获取颜色**
fun Int.getColor(context: Context):Int





## DeviceUtils

**判断设备是否 root**
fun isDeviceRooted(): Boolean


**判断设备 ADB 是否可用**
fun isAdbEnabled(context: Context): Boolean


**获取设备系统版本号**
fun getSDKVersionName(): String?


**获取设备系统版本码**
fun getSDKVersionCode(): Int


**获取设备 AndroidID**
fun getAndroidID(context: Context): String


**获取设备厂商**
fun getManufacturer(): String?


**获取设备型号**
fun getModel(): String


**获取设备 ABIs(cpu类型)**
fun getABIs(): Array<String>


**判断是否是平板**
fun isTablet(): Boolean


**判断是否是模拟器**
fun isEmulator(context: Context): Boolean


**开发者选项是否打开**
fun isDevelopmentSettingsEnabled(context: Context): Boolean





## StringUtils

**从String中获取**
fun Int.getString(context: Context): String

**从String中获取**
fun getString(context: Context,@StringRes resId: Int):String





## ScreenUtils

**获取屏幕宽度**
fun getScreenWidth(context: Context): Int


**获取屏幕高度**
fun getScreenHeight(context: Context): Int





## NotificationUtils

**消息通知是否开启**
fun isNotificationEnabled(context: Context?): Boolean





## TextViewUtils
**动态设置textview的drawableLeft**
fun TextView.drawableLeft(@DrawableRes resId: Int)

**动态设置textview的drawableRight**
fun TextView.drawableRight(@DrawableRes resId: Int)


**设置textview字体颜色**
fun TextView.textColor(@ColorRes colorId: Int)

**清除textView的drawable**
fun TextView.clearDrawable()


## ViewUtils
**从view中获取bitmap**
fun getBitmapByView(v: View): Bitmap?




## MediaUtils
**更新媒体库1**
fun refresh(cxt: Context, vararg filePaths: String)

**更新媒体库2**
fun refresh(cxt: Context, vararg files: File)

**更新媒体库3**
fun refresh(cxt: Context, filePathList: List<String>)


## KtUtils
**如果为null,返回block内容**
fun <T> T?.ifNull(block: () -> T): T

**String如果为empty,返回block内容**
fun String.ifEmpty(block: () -> String): String

## ActivityCollector
**添加Activity**
fun addActivity(activity: Activity)

**删除Activity**
fun removeActivity(activity: Activity)

**关闭指定的Activity**
fun finishOneActivity(vararg activityName: String)

**只保留某个Activity，关闭其他所有Activity**
fun finishOtherActivity(vararg activityName: String)

**关闭所有Activity**
fun finishAll()

## ViewModelExt
**处理retrofit等的协程异常捕获**
fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit, onError: (e: Throwable) -> Unit, onStart: () -> Unit, onComplete: () -> Unit = {})

## SAFUtils
**判断是否已经获取了指定Data目录权限**
fun getSAFGrant(targetPath: String)

**获取指定Data目录权限**
fun getSAFGrant(targetPath: String, startActivity: ActivityResultLauncher<Intent>?)

**在onActivityResult中的回调,并保存这个目录的访问权限**
fun registerForActivityResult(activity: AppCompatActivity)

**直接返回DocumentFile,可通过DocumentFile获取到name,uri等信息**
fun getDocumentFilePath(targetPath: String)
