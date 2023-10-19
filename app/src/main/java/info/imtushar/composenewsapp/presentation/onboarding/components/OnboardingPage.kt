package info.imtushar.composenewsapp.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import info.imtushar.composenewsapp.R
import info.imtushar.composenewsapp.presentation.Dimens.MediumPadding1
import info.imtushar.composenewsapp.presentation.Dimens.MediumPadding2
import info.imtushar.composenewsapp.presentation.onboarding.Page
import info.imtushar.composenewsapp.presentation.onboarding.pages
import info.imtushar.composenewsapp.ui.theme.ComposeNewsAppTheme

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = "page image",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )

        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview( uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnboardingPagePreview() {
    ComposeNewsAppTheme {
        Surface {
            OnboardingPage(page = pages[1])
        }

    }

}