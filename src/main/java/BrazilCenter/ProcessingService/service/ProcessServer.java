package BrazilCenter.ProcessingService.service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import BrazilCenter.DaoUtils.dao.Storager;
import BrazilCenter.Process.MqInterface.MqConnector;
import BrazilCenter.ProcessingService.Utils.Configuration;
import BrazilCenter.ProcessingService.Utils.LogUtils;
import BrazilCenter.ProcessingService.Utils.XMLOperator;

public class ProcessServer extends MqConnector implements Consumer{

	private ThreadPoolExecutor execSvc;
	private Storager storager;
	
	public ProcessServer(Configuration conf) throws IOException {
		super("BrazilStoreQueue", conf.getMqHostIp(), conf.getMqUserName(), conf.getMqPasswd());
		this.execSvc = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.storager = (Storager) context.getBean("storager");
		this.storager.setRootDir(conf.getRootDir());

	}

	/**
	 * Waits until there is no more active tasks
	 */
	private void waitForTasksToFinish() {
		
		if (this.execSvc != null) {
			int nActive = this.execSvc.getActiveCount();
			LogUtils.logger
					.info("Wait until all active tasks have finished. Current active tasks = [ " + nActive + " ]");

			while ((nActive = this.execSvc.getActiveCount()) > 0) {
				if (LogUtils.logger.isDebugEnabled()) {
					LogUtils.logger.debug("There are still [ " + nActive + " ] active tasks.");
				}
				try {
					LogUtils.logger.info("Sleepping at IngestServer.waitForTasksToFinish()");
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void start(){
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {

		XMLOperator xmlParser = new XMLOperator();
		xmlParser.Initial();
		Configuration conf = xmlParser.getConfiguration();
		
		ProcessServer processServer = null;
		try {
			processServer = new ProcessServer(conf);
			processServer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void handleDelivery(String arg0, Envelope arg1, BasicProperties arg2, byte[] body) throws IOException {
		// TODO Auto-generated method stub
		
		String fileName = new String(body, "UTF-8");
		LogUtils.logger.info("Received :" + fileName);
		
		Processor processThread = new Processor(fileName); // file name with file path
		processThread.setStorager(storager);

		this.execSvc.execute(processThread);
		
	}
	
	public void handleConsumeOk(String consumerTag) {}
	public void handleCancelOk(String consumerTag) {}
	public void handleCancel(String consumerTag) throws IOException {}
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {}
	public void handleRecoverOk(String consumerTag) {}
}
