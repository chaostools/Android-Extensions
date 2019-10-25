package com.tunjid.androidx.viewmodels

import android.app.Application
import android.view.ContextThemeWrapper
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.tunjid.androidx.R
import com.tunjid.androidx.baseclasses.AppBaseFragment
import com.tunjid.androidx.core.content.resolveThemeColor
import com.tunjid.androidx.core.text.SpanBuilder
import com.tunjid.androidx.fragments.*
import com.tunjid.androidx.model.Route

class RouteViewModel(application: Application) : AndroidViewModel(application) {

    private val mapping = listOf(
            listOf(
                    Route(DoggoListFragment::class.java.routeName, formatRoute(R.string.route_doggo_list)),
                    Route(IndependentStacksFragment::class.java.routeName, formatRoute(R.string.route_independent_stack)),
                    Route(MultipleStacksFragment::class.java.routeName, formatRoute(R.string.route_multiple_inner_stack))
            ),
            listOf(
                    Route(DoggoRankFragment::class.java.routeName, formatRoute(R.string.route_doggo_rank)),
                    Route(ShiftingTilesFragment::class.java.routeName, formatRoute(R.string.route_shifting_tile)),
                    Route(EndlessTilesFragment::class.java.routeName, formatRoute(R.string.route_endless_tile))
            ),
            listOf(
                    Route(BleScanFragment::class.java.routeName, formatRoute(R.string.route_ble_scan)),
                    Route(NsdScanFragment::class.java.routeName, formatRoute(R.string.route_nsd_scan))
            ),
            listOf(
                    Route(HidingViewsFragment::class.java.routeName, formatRoute(R.string.route_hiding_view)),
                    Route(SpanbuilderFragment::class.java.routeName, formatRoute(R.string.route_span_builder)),
                    Route(HardServiceConnectionFragment::class.java.routeName, formatRoute(R.string.route_hard_service_connection))
            )
    )

    operator fun get(@IdRes index: Int): List<Route> = mapping[index]

    private fun formatRoute(@StringRes stringRes: Int): CharSequence = ContextThemeWrapper(getApplication(), R.style.AppTheme).run {
        SpanBuilder.of(getString(stringRes))
                .italic()
                .underline()
                .color(this.resolveThemeColor(R.attr.prominent_text_color))
                .build()
    }
}


val <T : AppBaseFragment> Class<T>.routeName
    get() = simpleName
            .replace("Fragment", "")
            .replace(
                    String.format("%s|%s|%s",
                            "(?<=[A-Z])(?=[A-Z][a-z])",
                            "(?<=[^A-Z])(?=[A-Z])",
                            "(?<=[A-Za-z])(?=[^A-Za-z])"
                    ).toRegex(),
                    " "
            )