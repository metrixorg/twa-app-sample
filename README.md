## MetrixSDK Android TWA Sample
<div dir="rtl">
  
  پروژه نمونه اندروید sdk متریکس، اطلاعات بیشتر در [متریکس](https://metrix.ir).
  
<h2>راه اندازی</h2>

برای راه اندازی ابتدا از مستندات [اینجا](https://metrix.ir/docs/sdk/android) استفاده نماید.

سپس ReceiverActivity را مطابق مانیفست به پروژه خود اضافه کنید.
در نظر داشته باشید که در هنگام اجرای این کار مقدار myscheme و my.app.package را با مقدار انحصاری خود جایگذاری کنید.

سپس از طریق TWA با فراخوانی "intent://metrix?key=value#Intent;scheme=myscheme;package=my.app.package;action=ir.metrix.NewEvent;end" (مقدار‌های متفاوت action وکوئری‌پارامتر‌های دلخواه که در ReceiverActivity تعریف شده‌اند) میتوانید با کد نیتیو ارتباط برقرار کنید.
</div>
