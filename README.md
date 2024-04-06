# FC온라인 전적 검색 어플

## ✨주제

- FC 온라인의 유저 정보를 제공
- 최근 공식 10경기 정보 제공
</br>

## 💻주요 기능


### 최근 검색어 저장

- Sharedpreference를 통해서 최근 검색한 유저의 검색어를 저장
- 최근 검색어를 통한 검색 기능
- 최근 검색어 삭제 기능
</br>

### 검색 유저의 정보 제공

- Nexson의 api를 통한 유저의 정보 제공
- 유저의 닉네임, 레벨, 등급 정보 제공
- 최근 10경기 정보 제공
</br>

### 공식 경기 정보 제공

- 경기의 시간, 승패, 사용 선수 정보 제공
- 해당 경기의 평균, 슈팅, 점유율, 패스 성공률, 코너킥 정보 제공
</br>

### 경기에 사용된 선수 정보 제공

- 각각 선수에 대한 이름, 선수 시즌, 강화 등급, 포지션 정보 제공
- 해당 경기속 선수가 미친 영향력 정보 제공(평점, 득점, 어시슽, 슛 등)
</br>

## 사용 기술 스택

- MVVM, ViewModel, LiveData, Hilt Rxjava
</br>

### MVVM 팬턴, ViewModel, LiveData적용

- Network, ViewModel, UI 형식의 구조화
- 각각의 ViewModel에서 비지니스로직을 처리 후 각각의 View에 데이터를 제공
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/804a88d6-7918-4264-ba32-058f7ed2848b" width="250" height="600">
</br>

### Hilt 적용
- Dagger2의 경우 러닝 커브가 높고 koin보다는 유지보수면에서 Hilt가 편리하다고 판단하여 hilt 사용
- hilt를 통한 Retorfit2, ViewModel 의존성처리를 하여 보일러플레이트 코드를 줄였음
- 두가지 다른 base url의 api를 hilt의 어노테이션을 사용하여 모델 작성

```
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaMetadataOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaMetadataRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FifaRetrofit

    @FifaMetadataOkHttpClient
    @Provides
    @Singleton
    fun provideFifaMetadataOkHttpClient(
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(headersInterceptor)
            .build()
    }

    @FifaMetadataRetrofit
    @Provides
    @Singleton
    fun provideFifaMetadataRetrofit(@FifaMetadataOkHttpClient okHttpClient: OkHttpClient): FIFAMetadataService {
        return Retrofit.Builder()
            .baseUrl(Constants.METADATA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build().create(FIFAMetadataService::class.java)
    }

    @FifaOkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(headersInterceptor)
            .build()
    }

    @FifaRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(@FifaOkHttpClient okHttpClient: OkHttpClient): FIFAService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build().create(FIFAService::class.java)
    }
```

### Rxjava로 리펙토링
- 원래 CallBack으로 구현되어 있던 비지니스로직을 Rxjava를 통한 리펙토링
- 여러 많은 api를 Rxjava로 비동기처리하여 한번에 가져옴
- 코드의 가독성 증가
```
    fun requestSpId() {
        fifaManager.requestSpId()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.e("cyc", "전체 선수 통신 성공")
                _spIdDTOList.postValue(it)
            }, {
                Log.e("cyc", "전체 선수 통신 실패 : ${it}")
            })
            .addTo(disposable)
    }

    fun requestSpPosition() {
        fifaManager.requestSpPosition()
            .subscribeOn(Schedulers.io())
            .subscribe({ positionList ->
                Log.e("cyc", "전체 포지션 성공")
                positionList?.let {
                    _spPositionDTOList.postValue(it)
                } ?: run {
                    Log.e("cyc", "전체 포지션 값이 null")
                }
            }, {
                Log.e("cyc", "전체 포지션 통신실패 : ${it}")
            })
            .addTo(disposable)
    }

    fun requestSeasonIdResult(){
        fifaManager.requestSeasonId()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.e("cyc", "전체 seasonId 데이터 통신 성공")
                _seasonIdResult.postValue(it)
            }, {
                Log.e("cyc", "전체 seasonId 데이터 통신 실패 : ${it}")
            })
            .addTo(disposable)
    }
```

## 스크린샷
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/8b39cb11-8024-43e5-84bc-fd44ed987cd4" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/67ec27da-e05a-4013-8992-76997aa231ce" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/a3bd373a-f162-4516-94aa-509da5bf6316" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/b7ac20b8-9ad7-4fee-9937-17068942cb2c" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/1c87c58b-7e5f-41af-b71a-270717b0f2bf" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/7452d577-2cbb-48d5-bbc5-c79b7b554f2a" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/fb1a9c09-20d0-4211-9f74-284645c038a6" width="200" height="400">
<img src="https://github.com/yoonchanchoi/FIFA/assets/74814647/fde5c206-af85-4ed8-88ef-8e89ecc8d37d" width="200" height="400">



