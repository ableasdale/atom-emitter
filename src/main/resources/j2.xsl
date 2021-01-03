<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
        version="3.0"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        xmlns:atom="http://www.w3.org/2005/Atom"
        xpath-default-namespace="http://www.w3.org/2005/Atom">
    <xsl:output indent="yes" omit-xml-declaration="yes" />
    <!--xsl:output method="html" indent="yes"/
      namespace="http://www.w3.org/2005/Atom"
     -->
    <xsl:template match="/atom:feed"> <!--xpath-default-namespace="http://www.w3.org/2005/Atom">-->
        <xsl:value-of select="xml-to-json(.)" />
    </xsl:template>
    <!--xsl:template match="atom:entry">
        <li>
            <h2><xsl:value-of select="atom:title" disable-output-escaping="yes"/></h2>
            <div><xsl:value-of select="atom:content" disable-output-escaping="yes"/></div>
        </li>
    </xsl:template

    <xsl:template match="data">
    <xsl:copy>
        <xsl:apply-templates select="json-to-xml(.)/*"/>
    </xsl:copy>
</xsl:template>

<xsl:template match="*[@key]" xpath-default-namespace="http://www.w3.org/2005/xpath-functions">
    <xsl:element name="{@key}">
        <xsl:apply-templates/>
    </xsl:element>
</xsl:template>



    -->
</xsl:stylesheet>