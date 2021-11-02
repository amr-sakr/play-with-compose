package com.example.playwithcompose.ui.starDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.playwithcompose.BuildConfig
import com.example.playwithcompose.R
import com.example.playwithcompose.entities.StarDetails
import timber.log.Timber


@Composable
fun StarDetailsScreen(
    viewModel: StarDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    state.value.star?.let { star ->
        StarProfile(star = star, imagesUrl = BuildConfig.IMAGES_URL + star.profilePath)
    }

    if (state.value.isLoading) {
        Timber.d(" Loading")
    }

    if (state.value.error.isNotEmpty()) {
        Timber.d(" Error")
    }
}

@Composable
fun StarProfile(star: StarDetails, imagesUrl: String) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        val (starImage, labelNameTv, starNameTv, labelPopularityTv, popularityTv, labelDepartmentTv, departmentTv) = createRefs()
        Image(
            painter = rememberImagePainter(
                imagesUrl + star.profilePath,
                builder = {
                    placeholder(
                        R.drawable.ic_launcher_foreground
                    )
                }),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .constrainAs(starImage) {
                    top.linkTo(parent.top, margin = 68.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text("Name",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(labelNameTv) {
                start.linkTo(parent.start)
                top.linkTo(starImage.bottom)
            }
        )


        Text(
            star.name!!,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.constrainAs(starNameTv) {
                baseline.linkTo(labelNameTv.baseline)
                end.linkTo(
                    parent.end, margin = 16.dp
                )
            }
        )

        Text("Popularity",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(labelPopularityTv) {
                top.linkTo(labelNameTv.bottom, margin = 16.dp)
            }
        )

        Text(
            "${star.popularity} %",
            style = MaterialTheme.typography.body2,
            modifier = Modifier.constrainAs(popularityTv) {
                baseline.linkTo(labelPopularityTv.baseline)
                end.linkTo(
                    parent.end, margin = 16.dp
                )
            }
        )


        Text("Department",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(labelDepartmentTv) {
                top.linkTo(labelPopularityTv.bottom, margin = 16.dp)
            }
        )



        Text(
            text = star.knownForDepartment!!,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.constrainAs(departmentTv) {
                baseline.linkTo(labelDepartmentTv.baseline)
                end.linkTo(
                    parent.end, margin = 16.dp
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    StarProfile(
        StarDetails(
            name = "name",
            popularity = 90.0,
            knownForDepartment = "Acting"
        ), ""
    )
}