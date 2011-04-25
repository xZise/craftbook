package com.sk89q.craftbook.ic;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.IC;

public abstract class GenericICFactory<T extends IC> extends AbstractICFactory {

    protected final Class<? extends IC> icClass;
    
    public GenericICFactory(Server server, Class<? extends IC> icClass) {
        super(server);
        this.icClass = icClass;
    }
        
    public static class DefaultICFactory<T extends DefaultIC> extends GenericICFactory<T> {

        public DefaultICFactory(Server server, Class<T> icClass) {
            super(server, icClass);
        }

        @Override
        protected void init(T ic, Sign sign) {
            ic.init(this.getServer(), sign);
        }
        
    }
    
    public static class RisingEdgeICFactory<T extends RisingEdgeIC> extends GenericICFactory<T> {

        private final boolean raisingEdge;
        
        public RisingEdgeICFactory(Server server, Class<T> icClass, boolean raisingEdge) {
            super(server, icClass);
            this.raisingEdge = raisingEdge;
        }

        @Override
        protected void init(T ic, Sign sign) {
            ic.init(this.getServer(), sign, raisingEdge);
        }
        
    }
    
     protected abstract void init(T ic, Sign sign);
    
    @SuppressWarnings("unchecked")
    @Override
    public IC create(Sign sign) {
        IC ic = null;
        try {
            ic = this.icClass.newInstance();
            this.init((T) ic, sign);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ic;
    }

}
