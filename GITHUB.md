# GitHub 同步说明

## 仓库信息

| 项目 | 内容 |
|------|------|
| 远程名称 | `origin` |
| 仓库地址 | https://github.com/1597531/toDoList.git |
| 默认分支 | `main` |

## 常用命令

在项目根目录 `d:\CODE\toDoList` 执行：

```bash
git status
git add .
git commit -m "简要说明本次改动"
git push
```

拉取远程更新：

```bash
git pull
```

首次克隆到其他电脑：

```bash
git clone https://github.com/1597531/toDoList.git
```

## 认证说明

使用 HTTPS 推送时，GitHub 已不再接受账户密码，需在 [Personal Access Tokens](https://github.com/settings/tokens) 创建 Token（勾选 `repo`），在提示输入密码时填入 Token。**请勿将 Token 提交到仓库或发给他人。**

## 更新记录

- 2026-05-10：连接远程 `1597531/toDoList`，合并远程 README 并完成首次推送；补充本文档与 Android 工程文件同步说明。
