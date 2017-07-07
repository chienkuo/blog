<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Akuo's Blog</title>
<#--<meta name="robots" content="noindex,follow">-->
    <link rel="alternate" type="application/rss+xml" title="Sela » Feed" href="https://akuo.me/feed/">
    <link rel="alternate" type="application/rss+xml" title="Sela » Comments Feed"
          href="https://akuo.me/comments/feed/">

    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css"
          type="text/css" media="all">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap-theme.min.css"
          type="text/css" media="all">
    <link rel="stylesheet" href="/static/bootstrap/css/blog.css"
          type="text/css" media="all">
</head>
<body>
<#include "common/nav.ftl">
<div class="container">
    <div class="blog-header">
        <h1 class="blog-title">Akuo's Blog</h1>
        <p class="lead blog-description"></p>
    </div>
    <div class="row">
        <div class="col-sm-8 blog-main">

        <#list model["posts"] as post>
            <div class="blog-post">
                <h2 class="blog-post-title">${post.title}</h2>
                <p class="blog-post-meta">${post.time} by <a href="#">${post.author}</a></p>

            <#noautoesc>
            ${post.content}
            </#noautoesc>

            </div>
        </#list>
            <nav>
                <ul class="pager">
                    <li><a href="#">Previous</a></li>
                    <li><a href="#">Next</a></li>
                </ul>
            </nav>

        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
            <div class="sidebar-module sidebar-module-inset">
                <h4>About</h4>
                <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet
                    fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
            </div>
            <div class="sidebar-module">
                <h4>Archives</h4>
                <ol class="list-unstyled">
                    <li><a href="#">March 2014</a></li>
                    <li><a href="#">February 2014</a></li>

                </ol>
            </div>
            <div class="sidebar-module">
                <h4>标签</h4>
                <ol class="list-unstyled">
                <#list model["tags"] as tag>
                    <li><a href="/tags/${tag.tagEnName}">${tag.tagName}</a></li>
                </#list>
                </ol>
            </div>
            <div class="sidebar-module">
                <h4>Elsewhere</h4>
                <ol class="list-unstyled">
                    <li><a href="#">GitHub</a></li>
                    <li><a href="#">Twitter</a></li>
                    <li><a href="#">Facebook</a></li>
                </ol>
            </div>
        </div><!-- /.blog-sidebar -->
    </div><!-- /.row -->
</div>
<#include "common/footer.ftl">
<script src="/static/jquery/jquery-3.2.1.min.js" type="text/javascript"
        defer=""></script>
<script src="/static/bootstrap/js/bootstrap.min.js" type="text/javascript"
        defer=""></script>
</body>
</html>