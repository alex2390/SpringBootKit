package pan.springbootkit.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * xpath解析xml
 *
 * Created by panzhangbao on 2019-10-03 16:01:53
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class PanXmlUtil implements Serializable {

    private static final long serialVersionUID = -8977990524195875186L;
    private final XPath path;
    private Document doc = null;

    private PanXmlUtil(InputSource inputSource) {
        DocumentBuilderFactory dbf = getDocumentBuilderFactory();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			doc = db.parse(inputSource);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		path = getXPathFactory().newXPath();
    }

    private static PanXmlUtil create(InputSource inputSource) {
		return new PanXmlUtil(inputSource);
    }

    public static PanXmlUtil of(InputStream is) {
        InputSource inputSource = new InputSource(is);
        return create(inputSource);
    }

    public static PanXmlUtil of(String xmlStr) {
        StringReader sr = new StringReader(xmlStr.trim());
        InputSource inputSource = new InputSource(sr);
        PanXmlUtil panXmlUtil = create(inputSource);
		if (sr != null) {
			sr.close();
		}
		return panXmlUtil;
    }

    private Object evalXPath(String expression, Object item, QName returnType) {
        item = null == item ? doc : item;
        try {
            return path.evaluate(expression, item, returnType);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取String
     * @param expression 路径
     * @return String
     */
    public String getString(String expression) {
        return (String) evalXPath(expression, null, XPathConstants.STRING);
    }

    /**
     * 获取Boolean
     * @param expression 路径
     * @return String
     */
    public Boolean getBoolean(String expression) {
        return (Boolean) evalXPath(expression, null, XPathConstants.BOOLEAN);
    }

    /**
     * 获取Number
     * @param expression 路径
     * @return {Number}
     */
    public Number getNumber(String expression) {
        return (Number) evalXPath(expression, null, XPathConstants.NUMBER);
    }

    /**
     * 获取某个节点
     * @param expression 路径
     * @return {Node}
     */
    public Node getNode(String expression) {
        return (Node) evalXPath(expression, null, XPathConstants.NODE);
    }

    /**
     * 获取子节点
     * @param expression 路径
     * @return NodeList
     */
    public NodeList getNodeList(String expression) {
        return (NodeList) evalXPath(expression, null, XPathConstants.NODESET);
    }


    /**
     * 获取String
     * @param node 节点
     * @param expression 相对于node的路径
     * @return String
     */
    public String getString(Object node, String expression) {
        return (String) evalXPath(expression, node, XPathConstants.STRING);
    }

    /**
     * 获取
     * @param node 节点
     * @param expression 相对于node的路径
     * @return String
     */
    public Boolean getBoolean(Object node, String expression) {
        return (Boolean) evalXPath(expression, node, XPathConstants.BOOLEAN);
    }

    /**
     * 获取
     * @param node 节点
     * @param expression 相对于node的路径
     * @return {Number}
     */
    public Number getNumber(Object node, String expression) {
        return (Number) evalXPath(expression, node, XPathConstants.NUMBER);
    }

    /**
     * 获取某个节点
     * @param node 节点
     * @param expression 路径
     * @return {Node}
     */
    public Node getNode(Object node, String expression) {
        return (Node) evalXPath(expression, node, XPathConstants.NODE);
    }

    /**
     * 获取子节点
     * @param node 节点
     * @param expression 相对于node的路径
     * @return NodeList
     */
    public NodeList getNodeList(Object node, String expression) {
        return (NodeList) evalXPath(expression, node, XPathConstants.NODESET);
    }

    /**
     * 针对没有嵌套节点的简单处理
     * @return map集合
     */
    public Map<String, String> toMap() {
        Element root = doc.getDocumentElement();
        Map<String, String> params = new HashMap<String, String>();

        // 将节点封装成map形式
        NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node instanceof Element) {
                params.put(node.getNodeName(), node.getTextContent());
            }
        }
        return params;
    }

    private static DocumentBuilderFactory getDocumentBuilderFactory(){
        return XmlHelperHolder.documentBuilderFactory;
    }

    private static XPathFactory getXPathFactory() {
        return  XmlHelperHolder.xPathFactory;
    }

    /**
     * 内部类单例
     */
    private static class XmlHelperHolder {
        private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        private static XPathFactory xPathFactory = XPathFactory.newInstance();
    }

}