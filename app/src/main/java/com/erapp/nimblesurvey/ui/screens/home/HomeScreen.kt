package com.erapp.nimblesurvey.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.erapp.nimblesurvey.R
import com.erapp.nimblesurvey.data.models.Survey
import com.erapp.nimblesurvey.ui.components.BottomLoaderShimmer
import com.erapp.nimblesurvey.ui.components.CarouselDots
import com.erapp.nimblesurvey.ui.components.ScreenWithMessage
import com.erapp.nimblesurvey.ui.screens.home.HomeScreenViewModel.HomeScreenEvent
import com.erapp.nimblesurvey.ui.screens.home.HomeScreenViewModel.HomeScreenState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    surveys: List<Survey> = emptyList(),
    homeScreenState: HomeScreenState = HomeScreenState.Idle,
    onEvent: (HomeScreenEvent) -> Unit = {},
    onSurveyButtonPressed: () -> Unit = {}
) {
    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        surveys.size
    }

    Box {
        when(homeScreenState) {
            is HomeScreenState.Error -> {
                // error screen
                ScreenWithMessage(
                    onRetry = { onEvent(HomeScreenEvent.OnRefreshSurveys) },
                )
            }
            HomeScreenState.Empty -> {
                // empty screen
                ScreenWithMessage(
                    errorMessage = stringResource(id = R.string.generic_empty_message),
                    onRetry = { onEvent(HomeScreenEvent.OnRefreshSurveys) },
                )
            }
            HomeScreenState.Success -> {
                Box {
                    HorizontalPager(
                        state = pagerState
                    ) { page ->
                        val survey = surveys.getOrNull(page)
                        CarouselCard(
                            name = survey?.attributes?.title.orEmpty(),
                            description = survey?.attributes?.description.orEmpty(),
                            imageUrl = survey?.attributes?.coverImageUrl.orEmpty(),
                            isLoading = homeScreenState is HomeScreenState.Loading,
                            goToDetails = onSurveyButtonPressed
                        )
                    }
                    CarouselDots(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(bottom = 148.dp, start = 16.dp),
                        dotsCount = surveys.size,
                        currentPage = pagerState.currentPage
                    )
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun CarouselCard(
    name: String,
    description: String,
    imageUrl: String,
    isLoading: Boolean = false,
    goToDetails: () -> Unit = {}
) {
    if (isLoading) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Black,
                                Color.Black
                            )
                        ),
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BottomLoaderShimmer()
                BottomLoaderShimmer()
            }
        }
    } else {
        Box {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        radiusX = 8.dp,
                        radiusY = 8.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(4.dp))
                    ),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.nimble_survey_bg_opacity)
                    .error(R.drawable.nimble_survey_bg_opacity)
                    .build(),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black)
                    )
                },
                contentDescription = null,
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.FillBounds,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.DarkGray,
                                Color.Black,
                                Color.Black
                            )
                        ),
                        alpha = 0.7f
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White,
                            ),
                        )
                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.Default.AccountCircle,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
                // bottom content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(96.dp)
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = description,
                            color = Color.White,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                        FloatingActionButton(
                            onClick = goToDetails,
                            backgroundColor = Color.White,
                        ) {
                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}