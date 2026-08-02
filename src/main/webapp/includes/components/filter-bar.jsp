<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card">

    <div class="filter-bar">

        <form method="get" class="filter-form">

            <div class="filter-search">

                <input
                        type="text"
                        class="form-control"
                        name="search"
                        value="${param.search}"
                        placeholder="Search...">

            </div>

            <div class="filter-status">

                <select
                        class="form-control"
                        name="status">

                    <option value="active"
                    ${param.status == 'active' ? 'selected' : ''}>
                        Active
                    </option>

                    <option value="inactive"
                    ${param.status == 'inactive' ? 'selected' : ''}>
                        Inactive
                    </option>

                    <option value="all"
                    ${param.status == 'all' ? 'selected' : ''}>
                        All
                    </option>

                </select>

            </div>

            <div class="filter-actions">

                <button
                        class="btn btn-primary"
                        type="submit">

                    <i class="bi bi-search"></i>

                    Search

                </button>

            </div>

        </form>

    </div>

</div>