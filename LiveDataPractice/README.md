# liveData+ViewModel+dataBinding Demo
## add dataBinding
android {
    ...
    buildFeatures {
     dataBinding true
    }
}

## add activity-ktx:1.8.0
如果不添加这个找不到类 import androidx.activity.viewModels

## add lifecycle-livedata-ktx
如果不添加则找不到 emit() 方法