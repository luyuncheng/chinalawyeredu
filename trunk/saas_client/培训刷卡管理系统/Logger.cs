//-------------------------------------------------------------------
//��Ȩ���У���Ȩ����(C) 2006��Microsoft(China) Co.,LTD
//ϵͳ���ƣ�GMCC-ADC
//�ļ����ƣ�
//ģ�����ƣ�
//ģ���ţ�
//�������ߣ�
//������ڣ�
//����˵����
//-----------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Text;

using log4net;

[assembly: log4net.Config.XmlConfigurator(Watch = true)]
namespace ��ѵˢ������ϵͳ
{
    public enum AppError
    {
        WARN = 0,
        EROR = 1,
        FATL = 2
    }
    /// <summary>
    /// �����ƣ�Logger
    /// ��˵����������־���������д��־����־Ŀ¼ͨ�������ļ��趨
    /// ���ߣ�  
    /// ������ڣ�
    /// </summary>
    public class Logger
    {
        private static readonly log4net.ILog loginfo  = log4net.LogManager.GetLogger("Logging.Info");
       
        public static void LogInfo(string msg)
        {
            StringBuilder logMessage = new StringBuilder();
            logMessage.Append(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss")).Append("=>").Append(msg);
          
            if (loginfo.IsInfoEnabled) 
                loginfo.Info( logMessage.ToString() );

        }

     
      
        public static void Log(string logmsg)
        {
           // log4net.LogManager.GetLogger(loggerName).Info(logmsg);
            loginfo.Info(logmsg);
        }

        public static ILog GetLogger(string loggerName)
        {
            return log4net.LogManager.GetLogger(loggerName);
        }
    }
}