package com.example.jokeapi.presentation.model

import android.view.View
import androidx.annotation.Nullable
import com.example.jokeapi.R
import com.example.jokeapi.utils.getStringResource

sealed class UiState {
    object Progress : UiState()

    class Content<T>(val data: T? = null) : UiState()

    class ServerError @Deprecated("user another constructor") constructor(val clickListener: View.OnClickListener?, val msg: String?) : UiState() {
        var errorCode: Int = 0

        constructor(
            msg: String? = getStringResource(R.string.server_error_message),
            @Nullable funBlock: (() -> Unit)?
        ) : this(View.OnClickListener { funBlock?.invoke() }, msg)

        constructor(
            errorCode: Int,
            msg: String? = null,
            @Nullable funBlock: (() -> Unit)?
        ) : this(View.OnClickListener { funBlock?.invoke() }, msg){
            this.errorCode = errorCode;

        }
    }

    class ServerDownError @Deprecated("user another constructor") constructor(@Nullable val clickListener: View.OnClickListener?, val msg: String?) : UiState() {
        constructor(
            msg: String? = getStringResource(R.string.server_error_message),
            @Nullable funBlock: (() -> Unit)?
        ) : this(View.OnClickListener { funBlock?.invoke() }, msg)

    }

    class NetworkError @Deprecated("user another constructor") constructor(@Nullable val clickListener: View.OnClickListener?, val msg: String?) : UiState() {

        constructor(
            msg: String? = getStringResource(R.string.no_internet_message),
            @Nullable funBlock: (() -> Unit)?
        ) : this(View.OnClickListener { funBlock?.invoke() }, msg)

    }
}
