/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */

package org.dspace.rdf.storage;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dspace.content.DSpaceObject;
import org.dspace.content.Site;
import org.dspace.content.factory.ContentServiceFactory;
import org.dspace.content.service.SiteService;
import org.dspace.core.Constants;
import org.dspace.core.Context;
import org.dspace.rdf.RDFConfiguration;

/**
 *
 * @author Pascal-Nicolas Becker (dspace -at- pascal -hyphen- becker -dot- de)
 */
public class LocalURIGenerator implements URIGenerator {
    private static final Logger log = Logger.getLogger(LocalURIGenerator.class);

    protected final SiteService siteService = ContentServiceFactory.getInstance().getSiteService();

    @Override
    public String generateIdentifier(Context context, int type, UUID id,
            String handle, List<String> identifiers)
            throws SQLException
    {
        String urlPrefix = RDFConfiguration.getDSpaceRDFModuleURI() + "/resource/";
        
        if (type == Constants.SITE)
        {
            return urlPrefix + siteService.findSite(context).getHandle();
        }
        
        if (type == Constants.COMMUNITY 
                || type == Constants.COLLECTION 
                || type == Constants.ITEM)
        {
            if (StringUtils.isEmpty(handle))
            {
                throw new IllegalArgumentException("Handle is null");
            }
            return urlPrefix + handle;
        }
        
        return null;
    }

    @Override
    public String generateIdentifier(Context context, DSpaceObject dso) throws SQLException {
        if (dso.getType() != Constants.SITE
                && dso.getType() != Constants.COMMUNITY
                && dso.getType() != Constants.COLLECTION
                && dso.getType() != Constants.ITEM)
        {
            return null;
        }
        
        return generateIdentifier(context, dso.getType(), dso.getID(), dso.getHandle(), ContentServiceFactory.getInstance().getDSpaceObjectService(dso).getIdentifiers(context, dso));
    }

}
