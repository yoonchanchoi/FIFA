package com.football.view.fifa.util

object Constants {

    //URL
    const val BASE_URL = "https://public.api.nexon.com/openapi/fconline/v1.0/"
    const val METADATA_BASE_URL = "https://static.api.nexon.co.kr/fconline/latest/"
    const val IMAGE_BASE_URL = "https://fco.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p%s.png"

    //Sharedpreference
    const val PREF_KEY_SEARCH = "key_search"
    const val PREF_KEY_ALL_SPID = "key_all_spid"
    const val PREF_KEY_ALL_SPPOSITION = "key_all_spposition"
    const val PREF_KEY_ALL_SEASONID = "key_all_seasonid"

    //최근기록에 저장할지 안할지
    const val RECENT_SEARCH_SAVE_TRUE = true
    const val RECENT_SEARCH_SAVE_FALSE = false

    //나의 팀인지 상대 팀인지 확인
    const val MY_TEAM_PLAYER_IMAGE = "my_team_player_image"
    const val OPPONENT_TEAM_PLAYER_IMAGE = "opponent_team_player_image"

    //선수 포지선의 int형 Keyr값

    const val SUB_SPOSITION = 28

    //bundleName
    const val BUNDLE_NAME_MATCHPLAYDTO = "bundle_name_matchPlayDTO"
}