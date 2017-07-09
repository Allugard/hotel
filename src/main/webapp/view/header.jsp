<nav>

    <ul>


        <li><a href="/" class="lnk"><fmt:message key="main.page"/></a></li>
        <li><a href="/login" class="lnk"><fmt:message key="login.page"/></a></li>
        <li><a href="/registration" class="lnk"><fmt:message key="registration.page"/></a></li>


        <%--<li>
            <form action="/home" method="get">
                <input type=submit value=<fmt:message key="main.page"/>>
                <input type="hidden" name="command" value="default"/>
            </form>
        </li>

        <li>
            <form action="/hotel/abc" method="get">
                <input type=submit value=<fmt:message key="registration.page"/>>

              <input type="hidden" name="command" value="forward"/>
              <input type="hidden" name="page" value="registration"/>

            </form>
        </li>

        <li>
            <form action="/hotel/abc" method="get">
                <input type=submit value=<fmt:message key="login.page"/>>
                <input type="hidden" name="command" value="forward"/>
                <input type="hidden" name="page" value="login"/>
            </form>
        </li>

        <li>
            <form action="/hotel/abc" method="get">
                <input type=submit value=<fmt:message key="apartment.page"/>>
                <input type="hidden" name="command" value="forward"/>
                <input type="hidden" name="page" value="apartment"/>
            </form>
        </li>--%>

        <c:out value="${language}"/>

        <li>
         <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
                <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukr</option>
                <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Ru</option>
            </select>
          </form>
        </li>

    </ul>
</nav>