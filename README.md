# Flamelink Android SDK

![logo](https://raw.githubusercontent.com/flamelink/flamelink/master/docs/_assets/icon.svg?sanitize=true)

> Easily integrate with your Flamelink CMS.

*THIS PACKAGE IS IN ALPHA - Please report any issues!*

This SDK is intended to be used on Android devices.

If you are unfamiliar with Flamelink, please visit our [flamelink.io](https://www.flamelink.io/) website for more info on features, pricing and more.

## Prerequisites

It goes without saying that you will need to have a [Flamelink](https://www.flamelink.io) project for this SDK to be of any use to you.

Although we would love to package and publish this module to the likes of jitpack for easy access - this SDK is still very much in ALPHA.

## Installation

Install as module in your current Android project alongside your codebase

```bash
git clone git@github.com:flamelink/flamelink-android-sdk.git
```

once the module is cloned inside your repo - remember to add the module to your root .gitignore project - we will be adding new and shiny features regularly

```bash
.gitignore

/flamelink-android-sdk
```

With `gradle`

You need to allow gradle to pickup the module and bundle it as part of your codebase add the following to your root `settings.gradle` file

```bash
':flamelink-android-sdk'
```

in your app `build.gradle` file add the following to your dependencies:

```bash
implementation project(":flamelink-android-sdk")
```

Since this SDK is built on top of the Firebase Android SDK please follow this link for the normal Firebase project setup: [Firebase](https://firebase.google.com/docs/android/setup)

> 🔥🔥🔥 **Flame on!!** 🔥🔥🔥