package com.sk89q.craftbook.gates.world;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.IC;

public abstract class GenericICFactory extends AbstractICFactory {

    protected final Class<? extends IC> icClass;
    
    public GenericICFactory(Server server, Class<? extends IC> icClass) {
        super(server);
        this.icClass = icClass;
    }
        
    public static class DefaultICFactory extends GenericICFactory {

        public DefaultICFactory(Server server, Class<? extends IC> icClass) {
            super(server, icClass);
        }

        @Override
        protected IC createNew(Sign sign) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
            Constructor<? extends IC> constructor = this.icClass.getConstructor(Server.class, Sign.class);
            return constructor.newInstance(this.getServer(), sign);
        }
        
    }
    
    public static class RaisingEdgeICFactory extends GenericICFactory {

        private final boolean raisingEdge;
        
        public RaisingEdgeICFactory(Server server, Class<? extends IC> icClass, boolean raisingEdge) {
            super(server, icClass);
            this.raisingEdge = raisingEdge;
        }

        @Override
        protected IC createNew(Sign sign) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
            Constructor<? extends IC> constructor = this.icClass.getConstructor(Server.class, Sign.class, Boolean.class);
            return constructor.newInstance(this.getServer(), sign, this.raisingEdge);
        }
        
    }
    
    protected abstract IC createNew(Sign sign) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException ;
    
    @Override
    public IC create(Sign sign) {
        IC ic = null;
        try {
            ic = this.createNew(sign);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
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
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ic;
    }

}
