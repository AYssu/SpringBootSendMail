# SpringBoot3 邮箱发送

- 普通文本邮件发送
- HTML邮件发送

该例子仅作为参考，实际项目请根据自身需求进行修改。
优化方向：
1. 添加邮件模板 美化邮件样式
2. 使用异步 优化前端响应时间
3. 不知道这玩意可以使用线程池不，能复用就好了，每次发送邮件都要认证一次，5~10秒左右

如果你有更好的建议和想法，欢迎提issue。