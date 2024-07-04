package basic.layouts.compose

/**
 * author: Frankie
 * Date: 2024/7/5
 * Description:
 */
data class Data(val drawable: Int,val text: Int)
class SampleData{
    companion object{
        val alignYourBodyData=listOf(
            Data(R.drawable.ab1_inversions,R.string.ab1_inversions),
            Data(R.drawable.ab2_quick_yoga,R.string.ab2_quick_yoga),
            Data(R.drawable.ab3_stretching,R.string.ab3_stretching),
            Data(R.drawable.ab4_tabata,R.string.ab4_tabata),
            Data(R.drawable.ab5_hiit,R.string.ab5_hiit),
        )
        val favoriteCollectionsData=listOf(
            Data(R.drawable.fc1_short_mantras,R.string.fc1_short_mantras),
            Data(R.drawable.fc2_nature_meditations,R.string.fc2_nature_meditations),
            Data(R.drawable.fc3_stress_and_anxiety,R.string.fc3_stress_and_anxiety),
            Data(R.drawable.fc4_self_massage,R.string.fc4_self_massage),
            Data(R.drawable.fc5_overwhelmed,R.string.fc5_overwhelmed),
            Data(R.drawable.fc6_nightly_wind_down,R.string.fc6_nightly_wind_down),
        )
    }
}