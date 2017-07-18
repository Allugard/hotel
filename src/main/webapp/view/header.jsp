<nav>

    <ul>


        <c:out value="${language}"/>
        <%--<li><a href="/" class="lnk"><fmt:message key="main.page"/></a></li>--%>
        <form action="/" method="post">
            <input type="hidden" name="command" value="redirect"/>
            <input type="hidden" name="page" value="index"/>
            <button type="submit"><fmt:message key="main.page"/></button>

        </form>

        <c:choose>
            <c:when test="${user == null}">
                <%--<li>
                    <a href="/login"><fmt:message key="login.page"/></a>
                </li>--%>

                <form action="/login" method="post">
                    <input type="hidden" name="command" value="redirect"/>
                    <input type="hidden" name="page" value="login"/>
                    <button type="submit"><fmt:message key="login.page"/></button>
                </form>



                <%--<li>
                    <a href="/registration"><fmt:message key="registration.page"/></a>
                </li>--%>

                <form action="/registration" method="post">
                    <input type="hidden" name="command" value="redirect"/>
                    <input type="hidden" name="page" value="registration"/>
                    <button type="submit"><fmt:message key="registration.page"/></button>
                </form>
            </c:when>



            <c:when test="${user != null}">
                <%--<li>
                    <a href="/profile"><fmt:message key="profile.page"/></a>
                </li>--%>

                <form action="/profile" method="post">
                    <input type="hidden" name="command" value="redirect"/>
                    <input type="hidden" name="page" value="profile"/>
                    <button type="submit"><fmt:message key="profile.page"/></button>
                </form>

                <li>
                    <fmt:message key="header.logged"/>
                    <c:out value="${user.userAuthentication.login}"/>
                </li>


                <form action="/logout" method="post">
                    <input type="hidden" name="command" value="logout"/>
                    <button type="submit"><fmt:message key="logout"/></button>
                </form>

                <%--<li>
                    <a href="/logout"><fmt:message key="logout"/></a>
                </li>--%>
                    <%--POST INSTEAD GET--%>
               <%-- <form action="your_url" method="post">
                        <button type="submit" name="your_name" value="your_value" class="btn-link">Go</button>
                    </form>--%>
            </c:when>

        </c:choose>


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