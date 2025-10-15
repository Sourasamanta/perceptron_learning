FROM openjdk:17-jdk-slim

ENV ANDROID_SDK_ROOT=/usr/local/android-sdk
ENV PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/cmdline-tools/bin:$ANDROID_SDK_ROOT/platform-tools

RUN apt-get update && apt-get install -y wget unzip git gradle && rm -rf /var/lib/apt/lists/*

RUN mkdir -p $ANDROID_SDK_ROOT/cmdline-tools \
    && cd $ANDROID_SDK_ROOT/cmdline-tools \
    && wget https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O tools.zip \
    && unzip tools.zip -d . \
    && rm tools.zip \
    && mkdir -p $ANDROID_SDK_ROOT/cmdline-tools/latest \
    && mv $ANDROID_SDK_ROOT/cmdline-tools/cmdline-tools/* $ANDROID_SDK_ROOT/cmdline-tools/latest/ \
    && rmdir $ANDROID_SDK_ROOT/cmdline-tools/cmdline-tools

RUN yes | $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$ANDROID_SDK_ROOT --licenses || true
RUN $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$ANDROID_SDK_ROOT "platform-tools" "platforms;android-36" "build-tools;36.0.0" "cmdline-tools;latest"

WORKDIR /app

COPY . .
RUN chmod +x gradlew

RUN ./gradlew assembleDebug

CMD ["/bin/bash"]
