package com.example.playwithcompose.ui.starsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.example.playwithcompose.BuildConfig
import com.example.playwithcompose.R
import com.example.playwithcompose.entities.StarView
import com.example.playwithcompose.ui.Screen
import com.example.playwithcompose.ui.theme.PlayWithComposeTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun StarsListScreen(viewModel: StarsViewModel = hiltViewModel(), navController: NavController) {
    StarsList(
        stars = viewModel.popularStars,
        BuildConfig.IMAGES_URL
    ) { starId ->
        navController.navigate(Screen.StarDetails.route + "/$starId")
    }
}

@Composable
fun StarsList(
    stars: Flow<PagingData<StarView>>,
    imagesUrl: String,
    listener: (id: Int) -> Unit
) {
    val lazyStarsItems: LazyPagingItems<StarView> = stars.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyStarsItems) { star ->
            StarsListItem(
                star = star!!,
                imagesUrl = imagesUrl,
                listener = listener
            )
        }

        lazyStarsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        //TODO view loading item
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = lazyStarsItems.loadState.refresh as LoadState.Error
                    item {
                        //TODO view error item
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = lazyStarsItems.loadState.append as LoadState.Error
                    item {
                        //TODO view error item
                    }
                }
            }
        }
    }
}


@Composable
fun StarsListItem(
    star: StarView,
    imagesUrl: String,
    modifier: Modifier = Modifier,
    listener: (id: Int) -> Unit
) {

    Row(
        modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable { listener(star.id!!) }

    ) {

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
                .align(Alignment.CenterVertically),
        )


        Spacer(modifier = modifier.width(16.dp))

        Column {
            Text(star.name!!, fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.height(8.dp))
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("${star.popularity} % Popularity", style = MaterialTheme.typography.body2)
                Spacer(modifier = modifier.height(4.dp))
                Text(star.gender.toString(), style = MaterialTheme.typography.body2)
            }

        }

    }


}


@Composable
fun LoadingItem() {

}


@Preview(showBackground = true)
@Composable
fun StarsListItemPreview() {
    PlayWithComposeTheme {
        val star = StarView(
            name = "Leonardo",
            popularity = 5.0,
            gender = "Male"
        )
        StarsListItem(star = star, "") { starId ->
            //TODO navigate
        }
    }
}