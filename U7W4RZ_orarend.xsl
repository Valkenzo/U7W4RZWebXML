<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" />

  <xsl:template match="/">
    <html>
      <head>
        <title>U7W4RZ Órarend</title>
        <style>
          table { border-collapse: collapse; width: 100%; }
          th, td { border: 1px solid #000; padding: 8px; text-align: left; }
          th { background-color: #f2f2f2; }
          .elmélet { background-color: #d9edf7; }
          .gyakorlat { background-color: #dff0d8; }
          h2 {text-align:center}
        </style>
      </head>
      <body>
        <h2>U7W4RZ Órarend</h2>
        <table>
          <tr>
            <th>Nap</th>
            <th>Idő</th>
            <th>Tárgy</th>
            <th>Típus</th>
            <th>Helyszín</th>
            <th>Oktató</th>
            <th>Szak</th>
          </tr>
          <xsl:for-each select="U7W4RZ_orarend/ora">
            <tr>
              <td>
                <xsl:value-of select="idopont/nap" />
              </td>
              <td>
                <xsl:value-of select="idopont/tol" /> - <xsl:value-of select="idopont/ig" />
              </td>
              <td>
                <xsl:value-of select="targy" />
              </td>
              <td class="{@tipus}">
                <xsl:value-of select="@tipus" />
              </td>
              <td>
                <xsl:value-of select="helyszin" />
              </td>
              <td>
                <xsl:value-of select="oktato" />
              </td>
              <td>
                <xsl:value-of select="szak" />
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>