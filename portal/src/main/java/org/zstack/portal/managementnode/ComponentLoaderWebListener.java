package org.zstack.portal.managementnode;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zstack.core.CoreGlobalProperty;
import org.zstack.core.Platform;
import org.zstack.core.cloudbus.CloudBus;
import org.zstack.header.exception.CloudRuntimeException;
import org.zstack.utils.BootErrorLog;
import org.zstack.utils.ExceptionDSL;
import org.zstack.utils.Utils;
import org.zstack.utils.logging.CLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.zstack.utils.ExceptionDSL.throwableSafe;

public class ComponentLoaderWebListener implements ServletContextListener {
    private static final CLogger logger = Utils.getLogger(ComponentLoaderWebListener.class);
    private static boolean isInit = false;
    private ManagementNodeManager node;
    private CloudBus bus;

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.warn("web listener issued context destroy event, start stopping process");
        if (isInit) {
            throwableSafe(new Runnable() {
                @Override
                public void run() {
                    node.stop();
                }
            });
        }
    }
/*RenJie:
 * 这里开始进行初始化操作。可以看到基本是创建并启动ManagementNode，同时创建云总线。想看
 * ManagementNode 启动过程还是想了解云总线？
*/    
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            if (!isInit) {
                Platform.createComponentLoaderFromWebApplicationContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
                node = Platform.getComponentLoader().getComponent(ManagementNodeManager.class);
                bus = Platform.getComponentLoader().getComponent(CloudBus.class);    //创建云总线，可以看看怎么创建的
                node.startNode();      //启动管理节点。具体细节我们进ManagementNodeManagerImpl里面看看
                isInit = true;
            }
        } catch (Throwable t) {
            logger.warn("failed to start management server", t);
            // have to call bus.stop() because its init has been called by spring
            if (bus != null) {
                bus.stop();
            }

            Throwable root = ExceptionDSL.getRootThrowable(t);
            new BootErrorLog().write(root.getMessage());
            if (CoreGlobalProperty.EXIT_JVM_ON_BOOT_FAILURE) {
                System.exit(1);
            } else {
                throw new CloudRuntimeException(t);
            }
        }
    }
}
