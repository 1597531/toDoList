# toDoList（列表清单）

一款基于 **Jetpack Compose** 的 Android 待办清单应用：浏览任务列表、编辑条目，数据使用 **Room** 持久化保存在本机。

## 功能概览

- 待办列表展示与基础交互
- 编辑 / 新建待办界面（Compose + ViewModel）
- 本地数据库（Room + Kotlin Coroutines）

## 技术栈

| 类别 | 说明 |
|------|------|
| 语言 | Kotlin |
| UI | Jetpack Compose、Material 3 |
| 架构 | ViewModel、Navigation Compose |
| 数据 | Room（KSP）、DAO / Entity |

**环境要求**：Android Studio（建议最新稳定版）、JDK 17、`compileSdk` / `targetSdk` 35，`minSdk` 26。

## 本地运行

1. 使用 Android Studio 打开本仓库根目录（含 `settings.gradle.kts` 的目录）。
2. 等待 Gradle 同步完成。
3. 连接设备或启动模拟器，点击 **Run** 运行 `app` 模块。

首次构建需联网下载依赖；若本机未配置 SDK，请在 Android Studio 中按提示安装。

## 仓库与协作

- **源码**：https://github.com/1597531/toDoList  
- 使用 Git 推送到 GitHub 的步骤与认证说明见 [GITHUB.md](GITHUB.md)。

## 许可证

若未另行声明，默认保留所有权利；如需开源许可可自行补充 `LICENSE` 文件。
