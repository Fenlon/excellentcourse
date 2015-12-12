package com.dph.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dph.dao.BBSMsgDao;
import com.dph.domain.BBSMsg;
import com.dph.domain.Card;
import com.dph.domain.QueryResult;
import com.dph.domain.User;
import com.dph.exception.DaoException;
import com.dph.service.Permission;
import com.dph.utils.DaoUtils;
import com.dph.utils.JdbcUtils;

public class BBSMsgDaoImpl implements BBSMsgDao
{

	private CardDaoImpl cardDaoImpl = new CardDaoImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.BBSMsgDao#addBBSMsg(com.dph.domain.BBSMsg)
	 */
	public void addBBSMsg(BBSMsg msg) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String parentid;
			if (msg.getParentmsg() == null)
			{
				parentid = null;
			} else
			{
				parentid = msg.getParentmsg().getId();
			}
			String sql = "insert into bbs_msg(id,content,time,username,parentmsgid,cardid,isread,parentusername)values(?,?,?,?,?,?,?,?) ";
			Object params[] = {msg.getId(), msg.getContent(), msg.getTime(),
					msg.getUser().getUsername(), parentid,
					msg.getCard().getId(), msg.isIsread(),
					msg.getParentusername()};
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.BBSMsgDao#findBBSMsg(java.lang.String)
	 */

	private void setCard2BBSMsg(BBSMsg msg) throws DaoException
	{
		String sql = "select cardid from bbs_msg where id=?";
		String cardid = DaoUtils.getData("cardid", msg.getId(), sql,
				String.class);
		Card card = cardDaoImpl.findCard(cardid);
		msg.setCard(card);
	}

	public BBSMsg findBBSMsg(String id) throws DaoException
	{
		try
		{
			if (id == "" || id == null)
			{
				return null;
			}
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from bbs_msg where id=?";
			// 得到消息自己
			BBSMsg msg = runner.query(conn, sql, id, new BeanHandler<BBSMsg>(
					BBSMsg.class));
			if (msg == null)
			{
				return null;
			}
			// 得到消息的作者

			sql = "select * from bbs_msg bbs , t_user u where bbs.id=? and bbs.username=u.username";
			User user = runner.query(conn, sql, id, new BeanHandler<User>(
					User.class));
			msg.setUser(user);

			setCard2BBSMsg(msg);
			String parentmsgid = runner.query(conn, sql, id,
					new ScalarHandler<String>("parentmsgid"));
			if (parentmsgid != null)
			{
				BBSMsg parent = runner.query(conn, sql, parentmsgid,
						new BeanHandler<BBSMsg>(BBSMsg.class));
				msg.setParentmsg(parent);
			}

			/*
			 * if (flag) { flag = false; // 得到父亲消息id String parentmsgid =
			 * runner.query(conn, sql, id, new
			 * ScalarHandler<String>("parentmsgid")); // 更具付清消息id 递归调用查处父亲消息
			 * BBSMsg parentmsg = findBBSMsg(parentmsgid); // shezhi father
			 * finish a whole msg if (parentmsg != null) {
			 * msg.setParentmsg(parentmsg); } } flag = true;
			 */
			return msg;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	private BBSMsg findParentMsg(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select parentmsgid from bbs_msg where id=?";
			// 得到父亲消息id
			String parentmsgid = runner.query(JdbcUtils.getConnection(), sql,
					id, new ScalarHandler<String>("parentmsgid"));
			// 更具付清消息id
			BBSMsg parentmsg = findBBSMsg(parentmsgid);
			return parentmsg;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.BBSMsgDao#delete(java.lang.String)
	 */
	@Permission("删除论坛回复")
	public void delete(String id) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "delete from bbs_msg where id=?";
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dph.dao.impl.BBSMsgDao#getPartMsg(int, int)
	 */
	public QueryResult getPartBBSMsg(String cardId, int startIndex, int pageSize)
			throws DaoException
	{
		try
		{
			Connection conn = JdbcUtils.getConnection();
			QueryResult result = new QueryResult();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from bbs_msg where cardid=? order by time limit ?,?";
			Object params[] = {cardId, startIndex, pageSize};
			List<BBSMsg> msgs = runner.query(conn, sql, params,
					new BeanListHandler<BBSMsg>(BBSMsg.class));

			if (msgs.size() == 0)
			{
				msgs = null;
			}
			if (msgs != null)
			{
				for (BBSMsg msg : msgs)
				{
					msg.setParentmsg(findParentMsg(msg.getId()));
					sql = "select u.* from bbs_msg bbs , t_user u where bbs.id=? and bbs.username=u.username";
					User user = runner.query(conn, sql, msg.getId(),
							new BeanHandler<User>(User.class));
					msg.setUser(user);
					// 下面的貌似还没有用到没必要显示，所以就不设置了节约点内存ok？
					// setCard2BBSMsg(msg);
				}
			}
			result.setList(msgs);
			int totalrecord = countMsgByCardID(cardId);
			result.setTotalrecord(totalrecord);
			return result;
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	private int countMsgByCardID(String cardId) throws DaoException
	{
		try
		{
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from bbs_msg where cardid=?";
			Object[] a = runner.query(JdbcUtils.getConnection(), sql, cardId,
					new ArrayHandler());
			return Integer.parseInt(a[0].toString());
		} catch (SQLException e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public <T> List<T> getListData(Object[] params, String sql, Class<T> clazz)
			throws DaoException
	{
		return DaoUtils.getListData(params, sql, clazz);
	}

	public List<BBSMsg> getRemindMsgs(String username) throws DaoException
	{
		try
		{
			String sql = "select * from bbs_msg where parentusername=? and isread=? order by time desc";
			Object[] params = {username, false};
			List<BBSMsg> msgs = DaoUtils.getListData(params, sql, BBSMsg.class);
			if (msgs == null)
			{
				return null;
			}

			QueryRunner runner = new QueryRunner();
			Connection conn = JdbcUtils.getConnection();
			for (BBSMsg msg : msgs)
			{
				msg.setParentmsg(findParentMsg(msg.getId()));
				sql = "select u.* from bbs_msg bbs , t_user u where bbs.id=? and bbs.username=u.username";
				User user = runner.query(conn, sql, msg.getId(),
						new BeanHandler<User>(User.class));
				msg.setUser(user);
				// 下面的貌似还没有用到没必要显示，所以就不设置了节约点内存ok？
				// setCard2BBSMsg(msg);
			}
			setCard(msgs);
			return msgs;
		} catch (Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	private List<BBSMsg> setCard(List<BBSMsg> msgs) throws DaoException
	{
		for (BBSMsg msg : msgs)
		{
			setCard2BBSMsg(msg);
		}
		return msgs;
	}

	public void update(String field, Object value, String id)
			throws DaoException
	{
		String sql = "update bbs_msg set " + field + "=? where id=?";
		DaoUtils.update(value, id, null, sql);
	}

}
