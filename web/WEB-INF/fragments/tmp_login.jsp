
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="search">
    <div class="tab">
        <h2>Search Property To Buy / Rent</h2>
    </div>
    <div class="container">
        <form id="form1" action="#">
            <table class="search_form" style="width:100%; border:none;">
                <tr>
                    <td width="10%" class="label">Address</td>
                    <td colspan="3"><label>
                            <input type="text" name="textfield" id="textfield" class="text longfield" />
                        </label></td>
                </tr>
                <tr>
                    <td class="label">&nbsp;</td>
                    <td colspan="3">Example : City, State / or use Zip Code - 123456</td>
                </tr>
                <tr>
                    <td class="label">Type</td>
                    <td width="34%"><label>
                            <select name="select" id="select" class="select_field">
                                <option>Flat</option>
                                <option>Apartment</option>
                                <option>House</option>
                                <option>Villa</option>
                                <option>Land</option>
                                <option>Estate</option>
                            </select>
                        </label></td>
                    <td width="14%" class="label">Minimum Price Rs.</td>
                    <td width="42%"><input type="text" name="textfield4" id="textfield4" class="text" /></td>
                </tr>
                <tr>
                    <td class="label">To</td>
                    <td><label>
                            <select name="select2" id="select2" class="select_field">
                                <option selected="selected">Buy</option>
                                <option>Rent</option>
                                <option>Search Both</option>
                            </select>
                        </label></td>
                    <td class="label">Maximum Price Rs.</td>
                    <td><input type="text" name="textfield2" id="textfield2" class="text" /></td>
                </tr>
                <tr>
                    <td class="label">Bed Rooms</td>
                    <td><label>
                            <input type="text" name="textfield5" id="textfield5" class="text smalltextarea" />
                        </label></td>
                    <td class="label">Bathrooms</td>
                    <td><input type="text" name="textfield3" id="textfield3" class="text" /></td>
                </tr>
                <tr>
                    <td class="label">Lot Size</td>
                    <td><label></label>
                        <input type="text" name="textfield7" id="textfield7" class="text" /></td>
                    <td class="label">UOM</td>
                    <td><input name="" type="radio" value="" />
                        </label>
                        Cents /
                        <input name="" type="radio" value="" />
                        </label>
                        Acres /
                        <input name="" type="radio" value="" />
                        Sq. Ft.</td>
                </tr>
                <tr>
                    <td class="label">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2" class="label"><label>index.html
                            <input type="image" src="${pageContext.request.contextPath}/images/searchbtn.gif" alt="search" name="button2" id="button2" value="Submit" />
                        </label></td>
                </tr>
            </table>
        </form>
    </div>
</div>