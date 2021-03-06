/*
 * Copyright (c) 2007 Stiftung Deutsches Elektronen-Synchrotron,
 * Member of the Helmholtz Association, (DESY), HAMBURG, GERMANY.
 *
 * THIS SOFTWARE IS PROVIDED UNDER THIS LICENSE ON AN "../AS IS" BASIS.
 * WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
 * THE USE OR OTHER DEALINGS IN THE SOFTWARE. SHOULD THE SOFTWARE PROVE DEFECTIVE
 * IN ANY RESPECT, THE USER ASSUMES THE COST OF ANY NECESSARY SERVICING, REPAIR OR
 * CORRECTION. THIS DISCLAIMER OF WARRANTY CONSTITUTES AN ESSENTIAL PART OF THIS LICENSE.
 * NO USE OF ANY SOFTWARE IS AUTHORIZED HEREUNDER EXCEPT UNDER THIS DISCLAIMER.
 * DESY HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS,
 * OR MODIFICATIONS.
 * THE FULL LICENSE SPECIFYING FOR THE SOFTWARE THE REDISTRIBUTION, MODIFICATION,
 * USAGE AND OTHER RIGHTS AND OBLIGATIONS IS INCLUDED WITH THE DISTRIBUTION OF THIS
 * PROJECT IN THE FILE LICENSE.HTML. IF THE LICENSE IS NOT INCLUDED YOU MAY FIND A COPY
 * AT HTTP://WWW.DESY.DE/LEGAL/LICENSE.HTM
 */
/*
 * $Id: DefaultEpicsAlarmForeground.java,v 1.6 2010/06/17 10:39:50 hrickens Exp $
 */
package org.csstudio.sds.cosyrules.color;

import org.csstudio.sds.model.IRule;
import org.csstudio.sds.util.ColorAndFontUtil;

/**
 * @author hrickens
 * @author $Author: hrickens $
 * @version $Revision: 1.6 $
 * @since 27.09.2007
 */
public class DefaultEpicsAlarmForeground implements IRule{
    /**
     * The ID for this rule.
     */
    public static final String TYPE_ID = "org.css.sds.color.default_epics_alarm_foreground";

    /**
     * Standard constructor.
     */
    public DefaultEpicsAlarmForeground() {
    }

    /**
     * {@inheritDoc}
     */
    public Object evaluate(final Object[] arguments) {
         if ((arguments != null) && (arguments.length > 0)) {
             double serverity = 0.0;
             if (arguments[0] instanceof Double) {
                  serverity = (Double) arguments[0];
             }else if (arguments[0] instanceof Long) {
                 serverity = ((Long)  arguments[0]).doubleValue();
             }
             if (serverity==2) {                            //Major - RED
                 return ColorAndFontUtil.toHex(205,0,0);
             }else if (serverity==1) {                      //Minor - Orange
                 return ColorAndFontUtil.toHex(255,127,0);
             }else if (serverity==0) {                      //No Alarm - Green
                 return ColorAndFontUtil.toHex(0,205,0);
             }else if (serverity==3) {                     //Invalid - White
                 return ColorAndFontUtil.toHex(0,178,238);
             }
         }
         return ColorAndFontUtil.toHex(138,43,226);    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

}
