<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
        version="1.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:atom="http://www.w3.org/2005/Atom">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:value-of select="atom:feed/atom:title"/></title>
                <link rel="stylesheet" href="/media/css/style.css" />
            </head>
            <body>
                <div id="wrapper">
                    <h1><xsl:value-of select="atom:feed/atom:title"/></h1>
                    <h2><xsl:value-of select="atom:feed/atom:subtitle"/></h2>
                    <div style="border:1px #ccc solid; padding:0 10px; margin-bottom:20px;">
                        <h3>What is this page?</h3>
                        <p>This is an RSS feed from the <xsl:value-of select="image/title" /> website. RSS feeds allow you to stay up to date with the latest news and features you want from  <xsl:value-of select="image/title" />.</p>
                        <p>To subscribe to it, you will need an RSS Reader. We recommend using the free <a href="http://www.google.com/reader">Google Reader</a>.</p>
                    </div>
                    <ul class="nobullets">
                        <xsl:apply-templates select="//atom:entry"/>
                    </ul>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="atom:entry">
        <li>
            <h2><xsl:value-of select="atom:title" disable-output-escaping="yes"/></h2>
            <div><xsl:value-of select="atom:content" disable-output-escaping="yes"/></div>
        </li>
    </xsl:template>
</xsl:stylesheet>